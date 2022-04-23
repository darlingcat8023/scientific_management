package com.personal.cl.handler.admin;

import com.personal.cl.model.RequestVerify;
import com.personal.cl.model.request.UserLoginRequest;
import com.personal.cl.model.response.AdminLoginResponse;
import com.personal.cl.service.admin.AdminLoginService;
import com.personal.cl.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
@Controller
@AllArgsConstructor
public class AdminLoginHandler {

    private final AdminLoginService adminLoginService;

    private final Validator validator;

    public Mono<ServerResponse> adminLogin(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(UserLoginRequest.class).log()
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, RequestVerify.UserLoginVerify.class));
        return ServerResponse.ok().body(this.adminLoginService.adminLogin(requestMono), AdminLoginResponse.class);
    }

}
