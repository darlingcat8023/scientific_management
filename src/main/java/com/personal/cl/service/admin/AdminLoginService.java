package com.personal.cl.service.admin;

import com.personal.cl.dao.AdminUserAccountRepository;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.AdminLoginRequest;
import com.personal.cl.model.response.AdminLoginResponse;
import com.personal.cl.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
@Service
@AllArgsConstructor
public class AdminLoginService {

    private final AdminUserAccountRepository adminUserAccountRepository;

    private final TokenService tokenService;

    public Mono<AdminLoginResponse> adminLogin(Mono<AdminLoginRequest> requestMono) {
        return requestMono.flatMap(request -> this.adminUserAccountRepository.findAdminUserAccountModelByAccountAndUserPassword(request.account(), request.password()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException("用户名或密码错误"))))
                .flatMap(model -> this.tokenService.generateAdminToken(model).map(token -> AdminLoginResponse.build(model, token)));
    }

}
