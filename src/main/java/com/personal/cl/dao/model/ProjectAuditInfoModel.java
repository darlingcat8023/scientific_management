package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "project_audit_info")
public record ProjectAuditInfoModel (

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        Integer projectId,

        Integer auditUserId,

        String auditUserName,

        Integer auditStep,

        Integer auditResult,

        Integer auditActive,

        String auditComment
) {}
