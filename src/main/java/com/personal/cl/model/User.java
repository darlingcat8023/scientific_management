package com.personal.cl.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author liujiajun
 * @date 4/2/22
 */
@JsonSerialize
@JsonDeserialize
public record User(
        //@JsonProperty(value = "nickName")
        String name,
        //@JsonProperty(value = "age")
        Integer age
) {
}
