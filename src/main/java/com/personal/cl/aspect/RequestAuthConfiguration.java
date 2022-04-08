package com.personal.cl.aspect;

import com.personal.cl.service.TokenService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Configuration(proxyBeanMethods = false)
@Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
public class RequestAuthConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public AnnotationResource<TokenCheck> tokenAnnotationResource() {
        return new AnnotationResource<>(TokenCheck.class);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public RequestAuthInterceptor requestAuthInterceptor(AnnotationResource<TokenCheck> resource, TokenService tokenService) {
        return new RequestAuthInterceptor(resource, tokenService);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public RequestAuthAdvisor requestAuthAdvisor(AnnotationResource<TokenCheck> resource, RequestAuthInterceptor interceptor) {
        return new RequestAuthAdvisor(resource, interceptor);
    }

}
