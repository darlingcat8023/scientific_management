package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectInfoModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Repository
public interface ProjectInfoRepository extends R2dbcRepository<ProjectInfoModel, Integer> {

    /**
     * 分页获取
     * @param collection
     * @param pageable
     * @return
     */
    Flux<ProjectInfoModel> findAllByIdIn(Collection<Integer> collection, Pageable pageable);

    /**
     * 更新项目状态
     * @param status
     * @param projectId
     * @return
     */
    @Modifying
    @Query(value = "update project_info set project_status = :status where id = :projectId ")
    Mono<Integer> updateProjectStatus(Integer status, Integer projectId);

}
