package com.personal.cl.service;

import com.personal.cl.dao.ProjectAuditInfoRepository;
import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.ProjectTypeRepository;
import com.personal.cl.dao.model.ProjectAuditInfoModel;
import com.personal.cl.dao.model.ProjectAuditRequest;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.response.ProjectAuditListResponse;
import com.personal.cl.model.response.ProjectListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProjectAuditService {

    private final ProjectAuditInfoRepository projectAuditInfoRepository;

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectTypeRepository projectTypeRepository;

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

    @Transactional(rollbackFor = {Exception.class})
    public Mono<String> pass(Mono<ProjectAuditRequest> requestMono) {
        return requestMono.flatMap(request -> this.projectAuditInfoRepository.findProjectAuditInfoModelByProjectIdAndAuditUserId(request.projectId(), request.auditUserId())
                .switchIfEmpty(Mono.error(new BusinessException("数据不存在")))
                .flatMap(audit -> this.projectAuditInfoRepository.updatePass(audit.id(), request.comment())
                        .then(this.projectAuditInfoRepository.activeNext(audit.projectId()))
                        .flatMap(i -> {
                            if (audit.auditStep().equals(2)) {
                                return this.projectInfoRepository.updateProjectStatus(audit.projectId(), 3)
                                        .then(this.projectInfoRepository.findById(audit.projectId())
                                                .flatMap(info -> this.projectTypeRepository.increasePassedProjects(info.projectType()))
                                        );
                            } else {
                                return Mono.empty();
                            }
                        })
                )
        ).map(x -> "success");
    }

    @Transactional(rollbackFor = {Exception.class})
    public Mono<String> reject(Mono<ProjectAuditRequest> requestMono) {
        return requestMono.flatMap(request -> this.projectAuditInfoRepository.findProjectAuditInfoModelByProjectIdAndAuditUserId(request.projectId(), request.auditUserId())
                .switchIfEmpty(Mono.error(new BusinessException("数据不存在")))
                .flatMap(audit -> this.projectAuditInfoRepository.updateReject(audit.id(), request.comment()))
                .then(this.projectInfoRepository.updateProjectRejects(request.projectId()))
                .flatMap(i -> this.projectInfoRepository.findById(request.projectId())
                        .flatMap(info -> {
                            if (info.projectRejects() > 2) {
                                return this.projectInfoRepository.updateProjectStatus(0, info.id())
                                        .then(this.projectTypeRepository.increaseRejectedProjects(info.projectType()));
                            } else {
                                return this.projectInfoRepository.updateProjectStatus(1, info.id());
                            }
                        })
                )
        ).map(x -> "success");
    }

}
