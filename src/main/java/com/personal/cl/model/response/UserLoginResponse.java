package com.personal.cl.model.response;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public record UserLoginResponse (
        String userName,
        String token
) {}
