package com.personal.cl.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author liujiajun
 * @date 4/4/22
 */
@Component
public class RequestAuthPointcut extends StaticMethodMatcherPointcutAdvisor {

    private final MethodInterceptor interceptor = Joinpoint::proceed;

    public RequestAuthPointcut() {
        this.setAdvice(this.interceptor);
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return false;
    }

}
