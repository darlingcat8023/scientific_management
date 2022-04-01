package com.personal.cl.utils;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author liujiajun
 * @date 4/2/22
 */
public class ReactiveServerWebExchangeHolder {

    public static final Class<ServerWebExchange> KEY = ServerWebExchange.class;

    /**
     * 获取请求上下文
     *
     * @return
     */
    public static Mono<ServerWebExchange> getServerWebExchange() {
        return Mono.deferContextual(contextView -> Mono.just(contextView.get(KEY)));
    }

}
