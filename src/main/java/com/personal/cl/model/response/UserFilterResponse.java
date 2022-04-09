package com.personal.cl.model.response;

import com.personal.cl.dao.model.UserAccountModel;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record UserFilterResponse (
        Integer userId,
        String userName,
        String userExtend
) {
    public static UserFilterResponse buildFromModel(UserAccountModel model) {
        return new UserFilterResponse(model.id(), model.userName(), model.userExtend());
    }
}
