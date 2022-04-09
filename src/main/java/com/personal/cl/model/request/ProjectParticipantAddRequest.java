package com.personal.cl.model.request;

import com.personal.cl.dao.model.ProjectParticipantInfoModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.personal.cl.model.RequestVerify.ProjectParticipantAddVerify;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record ProjectParticipantAddRequest (

        @NotNull(message = "用户id不能为空", groups = {ProjectParticipantAddVerify.class})
        Integer userId,

        @NotBlank(message = "用户名不能为空", groups = {ProjectParticipantAddVerify.class})
        String userName,

        @NotNull(message = "用户角色不能为空", groups = {ProjectParticipantAddVerify.class})
        Integer userRole

) {
        public ProjectParticipantInfoModel convertModel(Integer projectId) {
                return new ProjectParticipantInfoModel(
                        null,
                        null,
                        null,
                        projectId,
                        this.userId,
                        this.userName,
                        this.userRole
                );
        }
}
