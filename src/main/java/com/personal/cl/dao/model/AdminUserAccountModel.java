package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
@Table(value = "admin_user_account")
public record AdminUserAccountModel(

        @Id
        Integer id,

        @ReadOnlyProperty
        Timestamp createdAt,

        @ReadOnlyProperty
        Timestamp updatedAt,

        String account,

        String userName,

        String userPassword
) {
}
