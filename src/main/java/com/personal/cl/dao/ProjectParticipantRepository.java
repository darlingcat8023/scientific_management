package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectParticipantInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Repository
public interface ProjectParticipantRepository extends R2dbcRepository<ProjectParticipantInfoModel, Integer> {

}
