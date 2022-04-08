package com.personal.cl.aspect;

import com.personal.cl.base.TokenInfo;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.service.TokenService;
import com.personal.cl.utils.ReactiveContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public class RequestAuthInterceptor implements MethodInterceptor {

    private final AnnotationResource<TokenCheck> resource;

    private final TokenService tokenService;

    public RequestAuthInterceptor(AnnotationResource<TokenCheck> resource, TokenService tokenService) {
        this.resource = resource;
        this.tokenService = tokenService;
    }

    @Override
    @SuppressWarnings("all")
    public Mono<Object> invoke(MethodInvocation methodInvocation) throws Throwable {
        var check = this.resource.getSource(methodInvocation.getMethod(), methodInvocation.getThis().getClass());
        if (check == null) {
            return (Mono<Object>) methodInvocation.proceed();
        }
        return ReactiveContextHolder.getServerWebExchange()
                .flatMap(exchange -> {
                    String tokenString = this.getTokenFromRequest(exchange.getRequest());
                    Mono<TokenInfo> tokenInfoMono;
                    if (TokenCheck.TokenType.USER.equals(check.value())) {
                        tokenInfoMono = this.tokenService.parseUserToken(tokenString);
                    } else {
                        tokenInfoMono = this.tokenService.parseAdminToken(tokenString);
                    }
                    return tokenInfoMono.doOnNext(info -> exchange.getAttributes().put("user", info));
                }).flatMap(context -> {
                    try {
                        return (Mono<Object>) methodInvocation.proceed();
                    } catch (Throwable e) {
                        return Mono.error(new BusinessException("未知异常"));
                    }
                });
    }

    private String getTokenFromRequest(ServerHttpRequest serverRequest) {
        var tokenString = serverRequest.getHeaders().getFirst("token");
        if (StringUtils.hasText(tokenString)) {
            return tokenString;
        }
        tokenString = serverRequest.getQueryParams().getFirst("token");
        if (StringUtils.hasText(tokenString)) {
            return tokenString;
        }
        throw new BusinessException("请求无token");
    }

}
