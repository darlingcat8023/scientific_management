package com.personal.cl.service;

import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.ProjectParticipantRepository;
import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.ProjectParticipantAddRequest;
import com.personal.cl.model.response.ProjectParticipantListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Service
@AllArgsConstructor
public class ProjectParticipantService {

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectParticipantRepository projectParticipantRepository;

    public Mono<String> addProjectParticipant(Integer projectId, Flux<ProjectParticipantAddRequest> requestFlux) {
        return this.projectInfoRepository.findById(projectId)
                .switchIfEmpty(Mono.error(new BusinessException("项目id不存在")))
                .doOnNext(project -> {
                    if (!project.projectStatus().equals(1)) {
                        throw new BusinessException("当前状态不允许修改");
                    }
                })
                .flatMapMany(project -> this.projectParticipantRepository.saveAll(requestFlux.map(item -> item.convertModel(projectId))))
                .then(Mono.just("success"));
    }

    public Mono<String> removeProjectParticipant(Integer projectId, Flux<Integer> requestFlux) {
        return this.projectInfoRepository.findById(projectId)
                .switchIfEmpty(Mono.error(new BusinessException("项目id不存在")))
                .doOnNext(project -> {
                    if (!project.projectStatus().equals(1)) {
                        throw new BusinessException("当前状态不允许修改");
                    }
                })
                .flatMap(project -> this.projectParticipantRepository.deleteById(requestFlux))
                .then(Mono.just("success"));
    }

    public Flux<ProjectParticipantListResponse> listProjectParticipant(Integer projectId, Integer userRole) {
        return this.projectParticipantRepository.findProjectParticipantInfoModelsByProjectIdAndUserRole(projectId, userRole)
                .map(ProjectParticipantListResponse::buildFromModel);
    }


}
