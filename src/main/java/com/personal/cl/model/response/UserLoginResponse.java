package com.personal.cl.model.response;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public record UserLoginResponse (
        Integer userId,
        String userName,
        String token
) {}
