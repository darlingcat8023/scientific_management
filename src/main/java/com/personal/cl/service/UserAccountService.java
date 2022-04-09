package com.personal.cl.service;

import com.personal.cl.dao.UserAccountRepository;
import com.personal.cl.model.response.UserDetailResponse;
import com.personal.cl.model.response.UserFilterResponse;
import com.personal.cl.model.response.UserSecurityDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public Flux<UserFilterResponse> filterUser(Integer userType) {
        return this.userAccountRepository.findUserAccountModelsByUserType(userType)
                .map(UserFilterResponse::buildFromModel);
    }

    public Mono<UserDetailResponse> detailUser(Integer userId) {
        return this.userAccountRepository.findById(userId)
                .map(UserDetailResponse::buildFromModel);
    }

    public Mono<UserSecurityDetailResponse> securityDetailUser(Integer userId) {
        return this.userAccountRepository.findById(userId)
                .map(UserSecurityDetailResponse::buildFromModel);
    }

}
