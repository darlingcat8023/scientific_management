package com.personal.cl.aspect;

import com.personal.cl.annotation.TokenCheck;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public class RequestAuthAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private final transient Pointcut pointcut;

    private final transient AnnotationResource<TokenCheck> resource ;

    public RequestAuthAdvisor(AnnotationResource<TokenCheck> resource, MethodInterceptor methodInterceptor) {
        this.pointcut = new RequestAuthAdvisor.RequestAuthVerifyPointCut();
        this.resource = resource;
        this.setAdvice(methodInterceptor);
    }


    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    private class RequestAuthVerifyPointCut extends StaticMethodMatcherPointcut {

        @Override
        public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
            return RequestAuthAdvisor.this.resource.getSource(method, targetClass) != null;
        }

    }


}
