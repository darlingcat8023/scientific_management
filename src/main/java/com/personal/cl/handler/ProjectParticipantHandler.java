package com.personal.cl.handler;

import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.request.ProjectParticipantAddRequest;
import com.personal.cl.service.ProjectParticipantService;
import com.personal.cl.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

import static com.personal.cl.model.RequestVerify.ProjectParticipantAddVerify;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Controller
@AllArgsConstructor
public class ProjectParticipantHandler {

    private final ProjectParticipantService projectParticipantService;

    private final Validator validator;

    public Mono<ServerResponse> addProjectParticipant(ServerRequest serverRequest) {
        var projectId = serverRequest.queryParam("projectId").map(Integer::parseInt).orElseThrow(() -> new BusinessException("项目id不能为空"));
        var requestFlux = serverRequest.bodyToFlux(ProjectParticipantAddRequest.class)
                .doOnNext(req -> ValidatorUtils.valid(this.validator, req, ProjectParticipantAddVerify.class ));
        return ServerResponse.ok().body(this.projectParticipantService.addProjectParticipant(projectId, requestFlux), String.class);
    }

    public Mono<ServerResponse> removeProjectParticipant(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> listProjectParticipant(ServerRequest serverRequest) {
        return null;
    }

}
