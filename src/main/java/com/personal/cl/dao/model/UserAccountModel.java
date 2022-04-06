package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "user_account")
public record UserAccountModel(

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        String userName,

        String userMobile,

        String userPassword,

        String userIdentity,

        Integer userType,

        String userExtend
) {}
