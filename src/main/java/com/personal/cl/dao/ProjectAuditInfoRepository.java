package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectAuditInfoModel;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
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
    Flux<ProjectAuditInfoModel> findProjectAuditInfoModelsByAuditUserIdAndAuditActive(Integer auditUserId, Integer active);

    /**
     * 根据项目id查找
     * @param projectId
     * @return
     */
    Flux<ProjectAuditInfoModel> findProjectAuditInfoModelsByProjectIdOrderByAuditStepAsc(Integer projectId);

    /**
     * 更新项目
     * @param auditId
     * @param comment
     * @return
     */
    @Modifying
    @Query(value = "update project_audit_info set audit_active = 0, audit_result = 2, audit_comment = :comment where id = :auditId")
    Mono<Integer> updatePass(Integer auditId, String comment);

    /**
     * 激活下一个节点
     * @param projectId
     * @return
     */
    @Modifying
    @Query(value = "update project_audit_info set audit_active = 1 where project_id = :projectId and audit_result = 1")
    Mono<Integer> activeNext(Integer projectId);

    /**
     * 删除id
     * @param projectId
     * @return
     */
    Mono<Void> deleteByProjectId(Integer projectId);

}
