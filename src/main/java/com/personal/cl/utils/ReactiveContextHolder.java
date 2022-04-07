package com.personal.cl.utils;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/2/22
 */
public abstract class ReactiveContextHolder {

    public static final Class<ServerWebExchange> KEY_SERVER_EXCHANGE = ServerWebExchange.class;

    /**
     * 获取请求上下文
     *
     * @return
     */
    public static Mono<ServerWebExchange> getServerWebExchange() {
        return Mono.deferContextual(contextView -> Mono.just(contextView.get(KEY_SERVER_EXCHANGE)));
    }

}
