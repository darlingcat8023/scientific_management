package com.personal.cl.service;

import com.personal.cl.dao.ProjectAuditInfoRepository;
import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.model.ProjectAuditInfoModel;
import com.personal.cl.model.response.ProjectAuditListResponse;
import com.personal.cl.model.response.ProjectListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProjectAuditService {

    private final ProjectAuditInfoRepository projectAuditInfoRepository;

    private final ProjectInfoRepository projectInfoRepository;

    public Flux<ProjectListResponse> listAuditProject(Integer userId, Pageable pageable) {
        return this.projectAuditInfoRepository.findProjectAuditInfoModelsByAuditUserIdAndAuditActive(userId, 1)
                .map(ProjectAuditInfoModel::projectId).distinct().collectList()
                .flatMapMany(collection -> this.projectInfoRepository.findAllByIdIn(collection, pageable))
                .map(ProjectListResponse::buildFromModel);
    }

    public Mono<Long> countAuditProject(Integer userId) {
        return this.projectAuditInfoRepository.findProjectAuditInfoModelsByAuditUserIdAndAuditActive(userId, 1)
                .map(ProjectAuditInfoModel::projectId).distinct().count();
    }

    public Flux<ProjectAuditListResponse> auditList(Integer projectId) {
        return this.projectAuditInfoRepository.findProjectAuditInfoModelsByProjectIdOrderByAuditStepAsc(projectId)
                .map(ProjectAuditListResponse::buildFromModel);
    }

}
