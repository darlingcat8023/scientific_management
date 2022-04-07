package com.personal.cl.utils;

import com.personal.cl.base.TokenInfo;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/2/22
 */
public abstract class ReactiveContextHolder {

    public static final Class<ServerWebExchange> KEY_SERVER_EXCHANGE = ServerWebExchange.class;

    public static final Class<TokenInfo> KEY_TOKEN_INFO = TokenInfo.class;

    /**
     * 获取请求上下文
     *
     * @return
     */
    public static Mono<ServerWebExchange> getServerWebExchange() {
        return Mono.deferContextual(contextView -> Mono.just(contextView.get(KEY_SERVER_EXCHANGE)));
    }

    /**
     * 获取用户信息上下文
     * @return
     */
    public static Mono<TokenInfo> getTokenInfo() {
        return Mono.deferContextual(contextView -> Mono.just(contextView.get(KEY_TOKEN_INFO)));
    }

}
