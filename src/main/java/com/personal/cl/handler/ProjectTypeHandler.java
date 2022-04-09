package com.personal.cl.handler;

import com.personal.cl.model.response.ProjectStatisticListResponse;
import com.personal.cl.model.response.ProjectTypeFilterResponse;
import com.personal.cl.service.ProjectTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class ProjectTypeHandler {

    private final ProjectTypeService projectTypeService;

    public Mono<ServerResponse> filter(ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.projectTypeService.filterProjectType(), ProjectTypeFilterResponse.class);
    }

    public Mono<ServerResponse> list(ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.projectTypeService.listProjectStatistic(), ProjectStatisticListResponse.class);
    }

}
