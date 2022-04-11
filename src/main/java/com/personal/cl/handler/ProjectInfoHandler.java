package com.personal.cl.handler;

import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.ProjectCommitRequest;
import com.personal.cl.model.request.ProjectCreateRequest;
import com.personal.cl.model.request.ProjectListRequest;
import com.personal.cl.model.request.ProjectUpdateRequest;
import com.personal.cl.model.response.ProjectListResponse;
import com.personal.cl.service.ProjectInfoService;
import com.personal.cl.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
        var requestMono = serverRequest.bodyToMono(ProjectCreateRequest.class).log()
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, ProjectCreateVerify.class));
        return ServerResponse.ok().body(this.projectInfoService.createProject(requestMono), Integer.class);
    }

    public Mono<ServerResponse> updateProject(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(ProjectUpdateRequest.class).log()
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, ProjectUpdateVerify.class));
        return ServerResponse.ok().body(this.projectInfoService.updateProject(requestMono), Integer.class);
    }

    public Mono<ServerResponse> listProjectByCreator(ServerRequest serverRequest) {
        var page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(1);
        return ServerResponse.ok().body(this.projectInfoService.listByCreator(this.buildRequestMono(serverRequest), PageRequest.of(page - 1, 10)), ProjectListResponse.class);
    }

    public Mono<ServerResponse> countProjectByCreator(ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.projectInfoService.countByCreator(this.buildRequestMono(serverRequest)), Long.class);
    }

    public Mono<ServerResponse> listProjectByParticipant(ServerRequest serverRequest) {
        var page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(1);
        return ServerResponse.ok().body(this.projectInfoService.listByParticipant(this.buildRequestMono(serverRequest), PageRequest.of(page - 1, 10)), ProjectListResponse.class);
    }

    public Mono<ServerResponse> countProjectByParticipant(ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.projectInfoService.countByParticipant(this.buildRequestMono(serverRequest)), Long.class);
    }

    private Mono<ProjectListRequest> buildRequestMono(ServerRequest serverRequest) {
        var userId = serverRequest.queryParam("userId")
                .map(Integer::parseInt).orElseThrow(() -> new BusinessException("没有用户id"));
        var projectName = serverRequest.queryParam("projectName").orElse(null);
        var projectType = serverRequest.queryParam("projectType").orElse(null);
        var status = serverRequest.queryParam("projectStatus")
                .filter(StringUtils::hasText).map(Integer::parseInt).orElse(null);
        var greaterThen = serverRequest.queryParam("greaterThen")
                .filter(StringUtils::hasText).map(Integer::parseInt).orElse(null);
        var lessThen = serverRequest.queryParam("lessThen")
                .filter(StringUtils::hasText).map(Integer::parseInt).orElse(null);
        return Mono.just(new ProjectListRequest(userId, projectName, projectType, status, greaterThen, lessThen)).log();
    }

    public Mono<ServerResponse> commitProject(ServerRequest serverRequest) {
        var requestMono = serverRequest.bodyToMono(ProjectCommitRequest.class);
        return ServerResponse.ok().body(this.projectInfoService.commitProject(requestMono), String.class);
    }

}
