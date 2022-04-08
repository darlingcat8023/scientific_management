package com.personal.cl.service;

import com.personal.cl.dao.ProjectTypeRepository;
import com.personal.cl.model.response.ProjectStatisticListResponse;
import com.personal.cl.model.response.ProjectTypeFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ProjectTypeService {

    private final ProjectTypeRepository projectTypeRepository;

    public Flux<ProjectTypeFilterResponse> filterProjectType() {
        return this.projectTypeRepository.findAll().map(ProjectTypeFilterResponse::buildFromModel);
    }

    public Flux<ProjectStatisticListResponse> listProjectStatistic() {
        return this.projectTypeRepository.findAll().map(ProjectStatisticListResponse::buildFromModel);
    }

}
