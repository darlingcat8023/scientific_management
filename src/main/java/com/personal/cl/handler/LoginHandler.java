package com.personal.cl.handler;

import com.personal.cl.dao.ResultRepository;
import com.personal.cl.dao.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ResultRepository resultRepository;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.resultRepository.findById(136), ResultModel.class);
    }

    public Mono<ServerResponse> register(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

}
