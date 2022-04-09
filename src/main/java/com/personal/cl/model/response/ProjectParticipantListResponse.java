package com.personal.cl.model.response;

import com.personal.cl.dao.model.ProjectParticipantInfoModel;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record ProjectParticipantListResponse (
        Integer id,
        Integer projectId,
        Integer userId,
        String userName,
        Integer userRole
) {
    public static ProjectParticipantListResponse buildFromModel(ProjectParticipantInfoModel model) {
            return new ProjectParticipantListResponse(
                    model.id(),
                    model.projectId(),
                    model.userId(),
                    model.userName(),
                    model.userRole()
            );
    }
}
