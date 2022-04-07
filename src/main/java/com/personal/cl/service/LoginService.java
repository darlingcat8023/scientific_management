package com.personal.cl.service;

import com.personal.cl.dao.UserAccountRepository;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.UserLoginRequest;
import com.personal.cl.model.request.UserRegisterRequest;
import com.personal.cl.model.response.UserLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/6/22
 */
@Service
@AllArgsConstructor
public class LoginService {

    private final UserAccountRepository userAccountRepository;

    private final TokenService tokenService;

    public Mono<UserLoginResponse> userLogin(Mono<UserLoginRequest> requestMono) {
        return requestMono.flatMap(request -> this.userAccountRepository.findUserAccountModelByUserMobileAndUserPassword(request.userMobile(), request.userPassword()))
                .switchIfEmpty(Mono.error(new BusinessException("用户名或密码错误")))
                .flatMap(model -> this.tokenService.generateUserToken(model).map(token -> new UserLoginResponse(model.userName(), token)));
    }

    public Mono<String> userResister(Mono<UserRegisterRequest> requestMono) {
        return requestMono.map(UserRegisterRequest::convertModel)
                .flatMap(model -> this.userAccountRepository.findUserAccountModelByUserMobile(model.userMobile())
                        .flatMap(exists -> Mono.error(new BusinessException("手机号已被注册")))
                        .switchIfEmpty(this.userAccountRepository.save(model))
                ).map(result -> "success");
    }

}
