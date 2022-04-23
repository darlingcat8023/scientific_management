package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectTypeInfoModel;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectTypeRepository extends R2dbcRepository<ProjectTypeInfoModel, Integer> {

    /**
     * 已创建的项目数 + 1
     * @param type
     * @return
     */
    @Modifying
    @Query(value = "update project_type_info set created_projects = created_projects + 1 where type_name = :type ")
    Mono<Integer> increaseCreatedProjects(String type);

    /**
     * 已通过的项目数 + 1
     * @param type
     * @return
     */
    @Modifying
    @Query(value = "update project_type_info set passed_projects = passed_projects + 1 where type_name = :type ")
    Mono<Integer> increasePassedProjects(String type);


}
