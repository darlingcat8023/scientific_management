package com.personal.cl.handler;

import com.personal.cl.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class ProjectFileHandler {

    public Mono<ServerResponse> uploadFile(ServerRequest request) {
        var body = request.multipartData().map(parts -> parts.get("file"))
                .flatMapMany(Flux::fromIterable).cast(FilePart.class);
        var projectId = request.queryParam("projectId")
                .filter(StringUtils::hasText).map(Integer::parseInt).orElseThrow(() -> new BusinessException("项目id不能为空"));
        return ServerResponse.ok().bodyValue("success");
    }

    public Mono<ServerResponse> downloadFile(ServerRequest request) {
        return ServerResponse.ok().bodyValue("success");
    }

}
