package com.personal.cl.model.response;

import com.personal.cl.dao.model.AdminUserAccountModel;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
public record AdminLoginResponse (
        Integer userId,
        String userName,
        String userAccount,
        String token
) {
    public static AdminLoginResponse build(AdminUserAccountModel accountModel, String token) {
        return new AdminLoginResponse(
                accountModel.id(),
                accountModel.userName(),
                accountModel.account(),
                token
        );
    }
}
