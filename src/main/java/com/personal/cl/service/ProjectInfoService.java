package com.personal.cl.service;

import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.ProjectParticipantRepository;
import com.personal.cl.dao.ProjectTypeRepository;
import com.personal.cl.dao.model.ProjectInfoModel;
import com.personal.cl.dao.model.ProjectParticipantInfoModel;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.ProjectCreateRequest;
import com.personal.cl.model.request.ProjectListRequest;
import com.personal.cl.model.request.ProjectUpdateRequest;
import com.personal.cl.model.response.ProjectListResponse;
import com.personal.cl.wrapper.ProjectInfoRepoWrapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProjectInfoService {

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectParticipantRepository projectParticipantRepository;

    private final ProjectInfoRepoWrapper projectInfoRepoWrapper;

    private final ProjectTypeRepository projectTypeRepository;

    @Transactional(rollbackFor = {Exception.class})
    public Mono<Integer> createProject(Mono<ProjectCreateRequest> requestMono) {
        return requestMono.map(ProjectCreateRequest::convertModel)
                .flatMap(model -> this.projectTypeRepository.increaseCreatedProjects(model.projectType())
                        .flatMap(res -> this.projectInfoRepository.save(model)))
                .map(ProjectInfoModel::id);
    }

    public Mono<Integer> updateProject(Mono<ProjectUpdateRequest> requestMono) {
        return requestMono.flatMap(request -> this.projectInfoRepository.findById(request.id())
                        .switchIfEmpty(Mono.error(new BusinessException("未查询到数据")))
                        .doOnNext(project -> {
                            if (!project.projectStatus().equals(1)) {
                                throw new BusinessException("当前状态不允许修改");
                            }
                        }).map(request::merge)
                        .flatMap(this.projectInfoRepository::save))
                .map(ProjectInfoModel::id);
    }

    public Flux<ProjectListResponse> listByCreator(Mono<ProjectListRequest> requestMono, Pageable pageable) {
        return requestMono.flatMapMany(request -> this.projectInfoRepoWrapper.listByCreator(request, pageable))
                .map(ProjectListResponse::buildFromModel);
    }

    public Mono<Long> countByCreator(Mono<ProjectListRequest> requestMono) {
        return requestMono.flatMap(this.projectInfoRepoWrapper::countByCreator);
    }

    public Flux<ProjectListResponse> listByParticipant(Mono<ProjectListRequest> requestMono, Pageable pageable) {
        return requestMono.flatMapMany(request -> this.projectParticipantRepository.findProjectParticipantInfoModelsByUserId(request.userId())
                        .map(ProjectParticipantInfoModel::projectId).distinct().collectList()
                        .flatMapMany(collection -> this.projectInfoRepository.findAllByIdIn(collection, pageable)))
                .map(ProjectListResponse::buildFromModel);
    }

    public Mono<Long> countByParticipant(Mono<ProjectListRequest> requestMono) {
        return requestMono.flatMap(request -> this.projectParticipantRepository.findProjectParticipantInfoModelsByUserId(request.userId())
                .map(ProjectParticipantInfoModel::projectId).distinct().count());
    }

}
