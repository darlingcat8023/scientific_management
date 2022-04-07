package com.personal.cl.service;

import com.personal.cl.dao.TokenInfoRepository;
import com.personal.cl.dao.model.UserAccountModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author liujiajun
 * @date 4/7/22
 */
@Service
@AllArgsConstructor
public class TokenService {

    private final TokenInfoRepository tokenInfoRepository;

    public Mono<String> generateToken(UserAccountModel model) {
        return Mono.just("token");
    }

}
