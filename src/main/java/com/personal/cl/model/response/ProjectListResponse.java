package com.personal.cl.model.response;

import com.personal.cl.dao.model.ProjectInfoModel;

import java.sql.Timestamp;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record ProjectListResponse (

        Integer id,

        Timestamp createdAt,

        Timestamp updatedAt,

        String projectName,

        String projectType,

        String researchDirection,

        Integer projectFund,

        String projectRemark,

        Integer projectStatus,

        Boolean enableCommit,

        Integer creator

) {
    public static ProjectListResponse buildFromModel(ProjectInfoModel model) {
        return new ProjectListResponse(
                model.id(),
                model.createdAt(),
                model.updatedAt(),
                model.projectName(),
                model.projectType(),
                model.researchDirection(),
                model.projectFund(),
                model.projectRemark(),
                model.projectStatus(),
                model.projectRejects() == 3,
                model.creator()
        );
    }
}
