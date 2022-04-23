package com.personal.cl.model.response;

import com.personal.cl.dao.model.ProjectAuditInfoModel;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
public record ProjectAuditListResponse(
        String step,
        String auditUserName,
        Integer status,
        String comment
) {
    public static ProjectAuditListResponse buildFromModel(ProjectAuditInfoModel model) {
        return new ProjectAuditListResponse(
                switch (model.auditStep()) {
                    case 1 -> "初审";
                    case 2 -> "终审";
                    default -> "undefine";
                },
                model.auditUserName(),
                model.auditResult(),
                model.auditComment()
        );
    }
}
