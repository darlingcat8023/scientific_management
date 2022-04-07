package com.personal.cl.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;

import static com.personal.cl.model.RequestVerify.UserLoginVerify;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@JsonDeserialize
public record UserLoginRequest(

        @NotBlank(message = "手机号不能为空", groups = {UserLoginVerify.class})
        String userMobile,

        @NotBlank(message = "密码不能为空", groups = {UserLoginVerify.class})
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String userPassword

) {
}
