package com.personal.cl.dao.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.personal.cl.model.RequestVerify.ProjectAuditVerify;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
public record ProjectAuditRequest(

        @NotNull(message = "审核id不能为空", groups = {ProjectAuditVerify.class})
        Integer projectId,

        @NotNull(message = "审核人不能为空", groups = {ProjectAuditVerify.class})
        Integer auditUserId,

        @NotBlank(message = "备注不能为空", groups = {ProjectAuditVerify.class})
        String comment

) {}
