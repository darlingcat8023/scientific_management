package com.personal.cl.handler;

import com.personal.cl.annotation.TokenCheck;
import com.personal.cl.base.TokenInfo;
import com.personal.cl.utils.ReactiveContextHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Controller
@AllArgsConstructor
public class ProjectHandler {

    @TokenCheck
    public Mono<ServerResponse> createProject(ServerRequest serverRequest) {
        Mono<Object> user = ReactiveContextHolder.getServerWebExchange()
                .map(x -> x.getAttributes().get("user"));
        return ServerResponse.ok().body(user, TokenInfo.class);
    }

}
