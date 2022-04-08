package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Table(value = "token_info")
public record TokenInfoModel (

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        String token
) {}
