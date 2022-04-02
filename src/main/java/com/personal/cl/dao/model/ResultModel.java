package com.personal.cl.dao.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@JsonSerialize
@Table("anxinsign_result")
public record ResultModel(
        @Id Integer id,
        String result,
        String note
) {
}
