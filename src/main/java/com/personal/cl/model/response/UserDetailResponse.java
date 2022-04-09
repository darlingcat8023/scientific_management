package com.personal.cl.model.response;

import com.personal.cl.dao.model.UserAccountModel;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record UserDetailResponse (

        Integer userId,

        String userName,

        String userMobile,

        String userIdentity,

        Integer userType,

        String userExtend
) {
    public static UserDetailResponse buildFromModel(UserAccountModel model) {
        return new UserDetailResponse(
                model.id(),
                model.userName(),
                model.userMobile(),
                model.userIdentity(),
                model.userType(),
                model.userExtend()
        );
    }
}
