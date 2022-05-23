package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "project_info")
public record ProjectInfoModel (

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        String projectName,

        String projectType,

        String researchDirection,

        String projectLevel,

        String projectSource,

        String projectPriority,

        Integer projectFund,

        String projectRemark,

        Integer projectStatus,

        Integer projectRejects,

        Integer creator
) {}
