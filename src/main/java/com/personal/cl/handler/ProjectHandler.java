package com.personal.cl.handler;

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

    public Mono<ServerResponse> createProject(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

    public Mono<ServerResponse> updateProject(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

    public Mono<ServerResponse> listProject(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

    public Mono<ServerResponse> commitProject(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

}
