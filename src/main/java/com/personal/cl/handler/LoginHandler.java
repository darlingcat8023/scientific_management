package com.personal.cl.handler;

import com.personal.cl.dao.model.UserAccountModel;
import com.personal.cl.model.request.UserLoginRequest;
import com.personal.cl.model.request.UserRegisterRequest;
import com.personal.cl.model.response.UserLoginResponse;
import com.personal.cl.service.LoginService;
import com.personal.cl.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

import static com.personal.cl.model.RequestVerify.UserLoginVerify;
import static com.personal.cl.model.RequestVerify.UserRegisterVerify;

/**
 * @author xiaowenrou
 * @date 4/1/22
 */
@Slf4j
@Controller
@AllArgsConstructor
public class LoginHandler {

    private final LoginService loginService;

    private final Validator validator;

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(UserLoginRequest.class).log()
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, UserLoginVerify.class));
        return ServerResponse.ok().body(this.loginService.userLogin(requestMono), UserLoginResponse.class);
    }

    public Mono<ServerResponse> register(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(UserRegisterRequest.class).log()
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, UserRegisterVerify.class));
        return ServerResponse.ok().body(this.loginService.userResister(requestMono), UserAccountModel.class);
    }

}
