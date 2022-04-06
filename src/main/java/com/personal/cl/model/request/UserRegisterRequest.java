package com.personal.cl.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.personal.cl.model.RequestVerify.UserRegisterVerify;

/**
 * @author liujiajun
 * @date 4/6/22
 */
@JsonSerialize
@JsonDeserialize
public record UserRegisterRequest(

        @NotBlank(message = "用户名不能为空", groups = {UserRegisterVerify.class})
        String userName,

        @NotBlank(message = "手机号不能为空", groups = {UserRegisterVerify.class})
        String userMobile,

        @NotBlank(message = "密码不能为空", groups = {UserRegisterVerify.class})
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String userPassword,

        @NotNull(message = "用户类型不能为空", groups = {UserRegisterVerify.class})
        Integer userType,

        @NotBlank(message = "用户信息不能为空", groups = {UserRegisterVerify.class})
        String userExtend
) {}
