package com.personal.cl.model.response;

import com.personal.cl.dao.model.ProjectTypeInfoModel;

public record ProjectTypeFilterResponse (String typeName) {

    public static ProjectTypeFilterResponse buildFromModel(ProjectTypeInfoModel model) {
        return new ProjectTypeFilterResponse(model.typeName());
    }

}
