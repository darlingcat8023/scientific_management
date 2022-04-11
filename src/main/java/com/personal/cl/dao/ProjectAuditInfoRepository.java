package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectAuditInfoModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectAuditInfoRepository extends R2dbcRepository<ProjectAuditInfoModel, Integer> {

    /**
     * 根据处理人查找
     * @param auditUserId
     * @param active
     * @return
     */
    Flux<ProjectAuditInfoModel> findProjectAuditInfoModelsByAuditUserIdAAndAuditActive(Integer auditUserId, Integer active);

    /**
     * 删除id
     * @param projectId
     * @return
     */
    Mono<Void> deleteByProjectId(Integer projectId);

}
