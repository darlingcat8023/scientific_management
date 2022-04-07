package com.personal.cl.config;

import com.personal.cl.exception.BusinessException;
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
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * @author xiaowenrou
 * @date 3/30/22
 */
@Configuration(proxyBeanMethods = false)
public class RouterFunctionConfiguration {

    @Bean
    public RouterFunction<ServerResponse> loginRouterFunction(LoginHandler loginHandler) {
        Supplier<RouterFunction<ServerResponse>> supplier = () -> RouterFunctions.route()
                .POST("/login", RequestPredicates.contentType(MediaType.APPLICATION_JSON), loginHandler::login)
                .POST("/register", RequestPredicates.contentType(MediaType.APPLICATION_JSON), loginHandler::register)
                .build();
        return RouterFunctions.route().path("/api", supplier).build();
    }

    /**
     * Webflux全局异常处理器
     */
    @Order(value = -2)
    @Configuration(proxyBeanMethods = false)
    public static class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

        public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
            super(errorAttributes, resources, applicationContext);
            this.setMessageWriters(serverCodecConfigurer.getWriters());
        }

        @Override
        protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
            HandlerFunction<ServerResponse> function = request -> ServerResponse.status(500)
                    .body(Mono.just(errorAttributes.getError(request))
                            .ofType(BusinessException.class)
                            .map(BusinessException::getMessage)
                            .defaultIfEmpty("fail"), String.class);
            return RouterFunctions.route(RequestPredicates.all(), function);
        }

    }

}
