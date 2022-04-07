package com.personal.cl.handler;

import com.personal.cl.annotation.TokenCheck;
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
        return ServerResponse.ok().bodyValue("pass");
    }

}
