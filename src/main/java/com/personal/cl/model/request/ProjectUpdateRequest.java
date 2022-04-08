package com.personal.cl.model.request;

import com.personal.cl.dao.model.ProjectInfoModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.personal.cl.model.RequestVerify.ProjectUpdateVerify;

/**
 * @author xiaowenrou
 * @date 4/8/22
 */
public record ProjectUpdateRequest (

        @NotNull(message = "项目id不能为空", groups = {ProjectUpdateVerify.class})
        Integer id,

        @NotBlank(message = "项目名不能为空", groups = {ProjectUpdateVerify.class})
        String projectName,

        @NotBlank(message = "研究方向不能为空", groups = {ProjectUpdateVerify.class})
        String researchDirection,

        @NotNull(message = "项目预算不能为空", groups = {ProjectUpdateVerify.class})
        Integer projectFund,

        @NotBlank(message = "项目备注不能为空", groups = {ProjectUpdateVerify.class})
        String projectRemark
) {
        public ProjectInfoModel merge(ProjectInfoModel model) {
                return new ProjectInfoModel(
                        model.id(),
                        null,
                        null,
                        this.projectName,
                        model.projectType(),
                        this.researchDirection,
                        this.projectFund,
                        this.projectRemark,
                        model.projectStatus(),
                        model.projectRejects(),
                        model.creator()
                );
        }
}
