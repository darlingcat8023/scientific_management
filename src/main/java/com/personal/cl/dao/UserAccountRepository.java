package com.personal.cl.dao;

import com.personal.cl.dao.model.UserAccountModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserAccountRepository extends R2dbcRepository<UserAccountModel, Integer> {

    /**
     * 根据手机号查询用户
     * @param userMobile
     * @return
     */
    Mono<UserAccountModel> findUserAccountModelByUserMobile(String userMobile);

    /**
     * 根据用户名和密码查询用户
     * @param userMobile
     * @param userPassword
     * @return
     */
    Mono<UserAccountModel> findUserAccountModelByUserMobileAndUserPassword(String userMobile, String userPassword);

    /**
     * 根据用户类型查询用户
     * @param userType
     * @return
     */
    Flux<UserAccountModel> findUserAccountModelsByUserType(Integer userType);

}
