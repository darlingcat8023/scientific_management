package com.personal.cl.service;

import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.ProjectParticipantRepository;
import com.personal.cl.model.request.ProjectParticipantAddRequest;
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
        return null;
    }


}
