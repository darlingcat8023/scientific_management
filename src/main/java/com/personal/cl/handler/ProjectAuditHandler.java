package com.personal.cl.handler;

import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.response.ProjectAuditListResponse;
import com.personal.cl.model.response.ProjectListResponse;
import com.personal.cl.service.ProjectAuditService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/23/22
 */
@Controller
@AllArgsConstructor
public class ProjectAuditHandler {

    private final ProjectAuditService projectAuditService;

    public Mono<ServerResponse> listAuditProject(ServerRequest serverRequest) {
        var userId = serverRequest.queryParam("userId")
                .map(Integer::parseInt).orElseThrow(() -> new BusinessException("没有用户id"));
        var page = serverRequest.queryParam("page").map(Integer::parseInt).orElse(1);
        return ServerResponse.ok().body(this.projectAuditService.listAuditProject(userId, PageRequest.of(page - 1, 10)), ProjectListResponse.class);
    }

    public Mono<ServerResponse> countAuditProject(ServerRequest serverRequest) {
        var userId = serverRequest.queryParam("userId")
                .map(Integer::parseInt).orElseThrow(() -> new BusinessException("没有用户id"));
        return ServerResponse.ok().body(this.projectAuditService.countAuditProject(userId), Long.class);
    }

    public Mono<ServerResponse> auditList(ServerRequest serverRequest) {
        var projectId = serverRequest.queryParam("projectId")
                .map(Integer::parseInt).orElseThrow(() -> new BusinessException("没有项目id"));
        return ServerResponse.ok().body(this.projectAuditService.auditList(projectId), ProjectAuditListResponse.class);
    }

}
