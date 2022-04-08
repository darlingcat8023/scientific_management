package com.personal.cl.model.response;

import com.personal.cl.dao.model.ProjectTypeInfoModel;

public record ProjectStatisticListResponse (
        Integer id,
        String typeName,
        Integer createdProjects,
        Integer passedProjects,
        Integer rejectedProjects
) {

    public static ProjectStatisticListResponse buildFromModel(ProjectTypeInfoModel model) {
        return new ProjectStatisticListResponse(model.id(), model.typeName(), model.createdProjects(), model.passedProjects(), model.rejectedProjects());
    }

}
