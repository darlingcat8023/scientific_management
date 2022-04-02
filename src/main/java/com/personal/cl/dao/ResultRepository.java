package com.personal.cl.dao;

import com.personal.cl.dao.model.ResultModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends R2dbcRepository<ResultModel, Integer> {
}
