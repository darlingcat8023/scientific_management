package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "project_type_info")
public record ProjectTypeInfoModel (

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        String typeName,

        Integer createdProjects,

        Integer passedProjects,

        Integer rejectedProjects
) {}
