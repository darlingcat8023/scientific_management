package com.personal.cl.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.cl.model.RequestVerify;

import javax.validation.constraints.NotBlank;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
public record AdminLoginRequest(

        @NotBlank(message = "手机号不能为空", groups = {RequestVerify.UserLoginVerify.class})
        String account,

        @NotBlank(message = "密码不能为空", groups = {RequestVerify.UserLoginVerify.class})
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password

) {
}
