package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectInfoRepository extends R2dbcRepository<ProjectInfoModel, Integer> {

}
