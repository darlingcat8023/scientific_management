package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectParticipantInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Repository
public interface ProjectParticipantRepository extends R2dbcRepository<ProjectParticipantInfoModel, Integer> {

    /**
     * 根据id删除
     * @param projectId
     * @return
     */
    Mono<Integer> deleteAllByProjectId(Integer projectId);

    /**
     * 根据projectId和用户角色获取用户列表
     * @param projectId
     * @param userRole
     * @return
     */
    Flux<ProjectParticipantInfoModel> findProjectParticipantInfoModelsByProjectIdAndUserRole(Integer projectId, Integer userRole);

    /**
     * 获取项目id
     * @param userId
     * @return
     */
    Flux<ProjectParticipantInfoModel> findProjectParticipantInfoModelsByUserId(Integer userId);

}
