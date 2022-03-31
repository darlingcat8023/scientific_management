package com.personal.cl.config;

import com.personal.cl.handler.LoginHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.Supplier;

/**
 * @author liujiajun
 * @date 3/30/22
 */
@Configuration(proxyBeanMethods = false)
public class RouterFunctionConfiguration {

    @Bean
    public RouterFunction<ServerResponse> loginRouterFunction(LoginHandler loginHandler) {
        Supplier<RouterFunction<ServerResponse>> supplier = () -> RouterFunctions.route()
                .POST("/login", loginHandler::login)
                .POST("/register", loginHandler::register)
                .build();
        return RouterFunctions.route()
                .path("/api", builder -> builder.nest(RequestPredicates.contentType(MediaType.APPLICATION_JSON), supplier))
                .build();
    }

    /**
     * Webflux全局异常处理器
     */
    @Configuration
    @Order(value = -2)
    public static class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

        public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
            super(errorAttributes, resources, applicationContext);
            this.setMessageWriters(serverCodecConfigurer.getWriters());
        }

        @Override
        protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
            return null;
        }

    }

}
