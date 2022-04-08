package com.personal.cl.service;

import com.personal.cl.dao.ProjectInfoRepository;
import com.personal.cl.dao.ProjectTypeRepository;
import com.personal.cl.model.request.ProjectCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProjectInfoService {

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectTypeRepository projectTypeRepository;

    public Mono<Integer> createProject(Mono<ProjectCreateRequest> requestMono) {
        return Mono.just(1);
    }

}
