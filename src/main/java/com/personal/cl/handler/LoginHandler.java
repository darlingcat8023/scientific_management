package com.personal.cl.handler;

import com.personal.cl.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author liujiajun
 * @date 4/1/22
 */
@Component
public class LoginHandler {

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return ServerResponse.ok().body(serverRequest.bodyToMono(User.class), User.class);
    }

    public Mono<ServerResponse> register(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

}
