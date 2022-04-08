package com.personal.cl.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.personal.cl.model.RequestVerify.ProjectCreateVerify;

public record ProjectCreateRequest (

        @NotBlank(message = "项目名不能为空", groups = {ProjectCreateVerify.class})
        String projectName,

        @NotBlank(message = "学科分类不能为空", groups = {ProjectCreateVerify.class})
        String projectType,

        @NotBlank(message = "研究方向不能为空", groups = {ProjectCreateVerify.class})
        String researchDirection,

        @NotNull(message = "项目预算不能为空", groups = {ProjectCreateVerify.class})
        Integer projectFund,

        @NotBlank(message = "项目备注不能为空", groups = {ProjectCreateVerify.class})
        String projectRemark
) {}
