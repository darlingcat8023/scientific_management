package com.personal.cl.base;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
public record TokenInfo(
        Integer userId,
        String userName,
        Integer isAdmin
) {}
