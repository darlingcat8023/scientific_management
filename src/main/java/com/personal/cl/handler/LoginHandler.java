package com.personal.cl.handler;

import com.personal.cl.model.request.UserRegisterRequest;
import com.personal.cl.service.LoginService;
import com.personal.cl.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

import static com.personal.cl.model.RequestVerify.UserRegisterVerify;

/**
 * @author liujiajun
 * @date 4/1/22
 */
@Component
public class LoginHandler {

    @Autowired
    private LoginService loginService;

    @Autowired
    private Validator validator;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

    public Mono<ServerResponse> register(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(UserRegisterRequest.class)
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, UserRegisterVerify.class));
        return ServerResponse.ok().body(requestMono, UserRegisterRequest.class);
    }

}
