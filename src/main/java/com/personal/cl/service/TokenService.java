package com.personal.cl.service;

import com.personal.cl.dao.TokenInfoRepository;
import com.personal.cl.dao.model.UserAccountModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Service
@AllArgsConstructor
public class TokenService {

    private static final String USER_TOKEN_SECRET       = "user";

    private static final String ADMIN_TOKEN_SECRET      = "admin";

    private final TokenInfoRepository tokenInfoRepository;

    public Mono<String> generateToken(UserAccountModel model) {
        return Mono.just("token");
    }

}
