package com.personal.cl.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_account")
public record UserAccountModel(
        @Id
        Integer id,
        String userName,
        String userMobile,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String userPassword,
        Integer userType,
        String userExtend
) {
}
