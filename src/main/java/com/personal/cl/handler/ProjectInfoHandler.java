package com.personal.cl.handler;

import com.personal.cl.model.request.ProjectCreateRequest;
import com.personal.cl.model.request.ProjectUpdateRequest;
import com.personal.cl.service.ProjectInfoService;
import com.personal.cl.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

import static com.personal.cl.model.RequestVerify.ProjectCreateVerify;
import static com.personal.cl.model.RequestVerify.ProjectUpdateVerify;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Controller
@AllArgsConstructor
public class ProjectInfoHandler {

    private final ProjectInfoService projectInfoService;

    private final Validator validator;

    public Mono<ServerResponse> createProject(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(ProjectCreateRequest.class)
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, ProjectCreateVerify.class));
        return ServerResponse.ok().body(this.projectInfoService.createProject(requestMono), Integer.class);
    }

    public Mono<ServerResponse> updateProject(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(ProjectUpdateRequest.class)
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, ProjectUpdateVerify.class));
        return ServerResponse.ok().body(this.projectInfoService.updateProject(requestMono), Integer.class);
    }

    public Mono<ServerResponse> listProject(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

    public Mono<ServerResponse> commitProject(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("success");
    }

}
