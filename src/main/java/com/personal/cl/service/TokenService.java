package com.personal.cl.service;

import com.personal.cl.base.TokenInfo;
import com.personal.cl.dao.TokenInfoRepository;
import com.personal.cl.dao.model.AdminUserAccountModel;
import com.personal.cl.dao.model.TokenInfoModel;
import com.personal.cl.dao.model.UserAccountModel;
import com.personal.cl.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author xiaowenrou
 * @date 4/7/22
 */
@Service
@AllArgsConstructor
public class TokenService {

    private static final String USER_TOKEN_SECRET       = "user";

    private static final String ADMIN_TOKEN_SECRET      = "admin";

    private final TokenInfoRepository tokenInfoRepository;

    public Mono<String> generateUserToken(UserAccountModel model) {
        return this.generateToken(model.id(), model.userName(), 0, USER_TOKEN_SECRET);
    }

    public Mono<String> generateAdminToken(AdminUserAccountModel model) {
        return this.generateToken(model.id(), model.account(), 1, ADMIN_TOKEN_SECRET);
    }

    private Mono<String> generateToken(Integer userId, String userName, Integer isAdmin, String secret) {
        var token = Jwts.builder().setSubject("user")
                .setClaims(Map.of("userId", userId, "userName", userName,"isAdmin", isAdmin, "timeStamp", System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        return this.tokenInfoRepository.save(new TokenInfoModel(null, null, null, token))
                .map(TokenInfoModel::token);
    }

    public Mono<TokenInfo> parseUserToken(String token) {
        return this.parseToken(token, 0, USER_TOKEN_SECRET);
    }

    public Mono<TokenInfo> parseAdminToken(String token) {
        return this.parseToken(token, 1, ADMIN_TOKEN_SECRET);
    }

    private Mono<TokenInfo> parseToken(String token, Integer admin, String secret) {
        Claims body;
        try {
            body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return Mono.error(new BusinessException("token????????????"));
        }
        var userId = body.get("userId", Integer.class);
        var userName = body.get("userName", String.class);
        var isAdmin = body.get("isAdmin", Integer.class);
        return this.tokenInfoRepository.queryTokenInfoModelByToken(token)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException("token??????"))))
                .flatMap(model -> {
                    if (!isAdmin.equals(admin)) {
                       return Mono.error(new BusinessException("?????????"));
                    }
                    return this.tokenInfoRepository.save(model);
                }).map(model -> new TokenInfo(userId, userName, isAdmin));
    }

}
