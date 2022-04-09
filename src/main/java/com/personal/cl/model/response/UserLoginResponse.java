package com.personal.cl.model.response;

import com.personal.cl.dao.model.UserAccountModel;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public record UserLoginResponse (
        Integer userId,
        String userName,
        String userMobile,
        String userIdentity,
        Integer userType,
        String userExtend,
        String token
) {
    public static UserLoginResponse build(UserAccountModel accountModel, String token) {
        return new UserLoginResponse(
                accountModel.id(),
                accountModel.userName(),
                accountModel.userMobile(),
                accountModel.userIdentity(),
                accountModel.userType(),
                accountModel.userExtend(),
                token
        );
    }
}
