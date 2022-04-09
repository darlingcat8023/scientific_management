package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Table(value = "project_participant_info")
public record ProjectParticipantInfoModel (

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        Integer projectId,

        Integer userId,

        String userName,

        Integer userRole
) {}
