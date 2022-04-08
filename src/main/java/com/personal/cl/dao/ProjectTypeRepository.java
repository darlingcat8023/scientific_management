package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectTypeInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTypeRepository extends R2dbcRepository<ProjectTypeInfoModel, Integer> {



}
