package com.personal.cl.aspect;

import com.personal.cl.annotation.TokenCheck;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.service.TokenService;
import com.personal.cl.utils.ReactiveContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
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
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TokenCheck tokenCheck = this.resource.getSource(invocation.getMethod(), invocation.getThis().getClass());
        if (tokenCheck == null) {
            return invocation.proceed();
        }
        return ReactiveContextHolder.getServerWebExchange()
                .flatMap(exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("token")))
                .switchIfEmpty(Mono.error(new BusinessException("未携带token"))).flatMap(token -> {
                    try {
                        return invocation.proceed();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

}
