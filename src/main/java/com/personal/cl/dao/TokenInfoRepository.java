package com.personal.cl.dao;

import com.personal.cl.dao.model.TokenInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liujiajun
 * @date 4/7/22
 */
@Repository
public interface TokenInfoRepository extends R2dbcRepository<TokenInfoModel, Integer> {



}
