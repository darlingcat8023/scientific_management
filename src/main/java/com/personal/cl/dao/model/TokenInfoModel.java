package com.personal.cl.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author liujiajun
 * @date 4/7/22
 */
@Table(value = "token_info")
public record TokenInfoModel(

        @Id
        Integer id,

        String token
) {}
