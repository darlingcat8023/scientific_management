package com.personal.cl.aspect;

import com.personal.cl.annotation.TokenCheck;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

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
    public Mono<Object> invoke(MethodInvocation methodInvocation) throws Throwable {
        TokenCheck check = this.resource.getSource(methodInvocation.getMethod(), methodInvocation.getMethod().getDeclaringClass());
        if (check == null) {
            methodInvocation.getMethod();
        }
        return ReactiveContextHolder.getServerWebExchange()
                .map(ServerWebExchange::getRequest)
                .flatMap(request -> {
                    String tokenString = this.getTokenFromRequest(request);
                    if (TokenCheck.TokenType.USER == check.value()) {
                        return this.tokenService.parseUserToken(tokenString);
                    } else {
                        return this.tokenService.parseAdminToken(tokenString);
                    }
                });
    }

    private String getTokenFromRequest(ServerHttpRequest serverRequest) {
        String tokenString = serverRequest.getHeaders().getFirst("token");
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
