package com.personal.cl.dao;

import com.personal.cl.dao.model.AdminUserAccountModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
@Repository
public interface AdminUserAccountRepository extends R2dbcRepository<AdminUserAccountModel, Integer> {

    /**
     * 根据用户名和密码查找
     * @param account
     * @param userPassword
     * @return
     */
    Mono<AdminUserAccountModel> findAdminUserAccountModelByAccountAndUserPassword(String account, String userPassword);

}
