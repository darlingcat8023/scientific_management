package com.personal.cl.model.request;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
public record ProjectListRequest (
        Integer userId,
        String projectType,
        Integer status
) {}
