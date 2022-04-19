package com.personal.cl.handler;

import com.personal.cl.exception.BusinessException;
import com.personal.cl.model.response.UserDetailResponse;
import com.personal.cl.model.response.UserFilterResponse;
import com.personal.cl.model.response.UserSecurityDetailResponse;
import com.personal.cl.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Controller
@AllArgsConstructor
public class UserAccountHandler {

    private final UserAccountService userAccountService;

    public Mono<ServerResponse> filter(ServerRequest serverRequest) {
        var userType = serverRequest.queryParam("userType").map(Integer::parseInt).orElseThrow(() -> new BusinessException("用户类型不能为空"));
        return ServerResponse.ok().body(this.userAccountService.filterUser(userType), UserFilterResponse.class);
    }

    public Mono<ServerResponse> detail(ServerRequest serverRequest) {
        var userId = serverRequest.queryParam("userId").map(Integer::parseInt).orElseThrow(() -> new BusinessException("用户id不能为空"));
        return ServerResponse.ok().body(this.userAccountService.detailUser(userId), UserDetailResponse.class);
    }

    public Mono<ServerResponse> securityDetail(ServerRequest serverRequest) {
        var userId = serverRequest.queryParam("userId").map(Integer::parseInt).orElseThrow(() -> new BusinessException("用户id不能为空"));
        return ServerResponse.ok().body(this.userAccountService.securityDetailUser(userId), UserSecurityDetailResponse.class);
    }

}
