package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.sql.Timestamp;

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
