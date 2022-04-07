package com.personal.cl.dao;

import com.personal.cl.dao.model.TokenInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Repository
public interface TokenInfoRepository extends R2dbcRepository<TokenInfoModel, Integer> {

    /**
     * 获取token
     * @param token
     * @return
     */
    Mono<TokenInfoModel> queryTokenInfoModelByToken(String token);

}
