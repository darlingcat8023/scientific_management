package com.personal.cl.model.response;

import com.personal.cl.annotation.SecuritySerialize;
import com.personal.cl.dao.model.UserAccountModel;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record UserSecurityDetailResponse (
        Integer userId,
        String userName,
        @SecuritySerialize
        String userMobile,
        @SecuritySerialize
        String userIdentity,
        Integer userType,
        String userExtend
) {
    public static UserSecurityDetailResponse buildFromModel(UserAccountModel model) {
        return new UserSecurityDetailResponse(
                model.id(),
                model.userName(),
                model.userMobile(),
                model.userIdentity(),
                model.userType(),
                model.userExtend()
        );
    }
}
