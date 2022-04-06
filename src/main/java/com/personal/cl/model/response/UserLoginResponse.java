package com.personal.cl.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author liujiajun
 * @date 4/7/22
 */
@JsonSerialize
public record UserLoginResponse(
        String userName,
        String token
) {
}
