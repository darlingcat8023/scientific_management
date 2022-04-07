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

    public RequestAuthInterceptor(AnnotationResource<TokenCheck> resource) {
        this.resource = resource;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return null;
    }

}
