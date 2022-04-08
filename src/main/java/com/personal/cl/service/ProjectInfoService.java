package com.personal.cl.service;

import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.ProjectTypeRepository;
import com.personal.cl.dao.model.ProjectInfoModel;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.ProjectCreateRequest;
import com.personal.cl.model.request.ProjectUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProjectInfoService {

    private final ProjectInfoRepository projectInfoRepository;

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

    public Mono listByCreator(Mono<> requestMono, ) {

    }

}
