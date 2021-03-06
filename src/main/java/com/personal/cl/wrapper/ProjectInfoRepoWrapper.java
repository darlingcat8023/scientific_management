package com.personal.cl.wrapper;

import com.personal.cl.dao.model.ProjectInfoModel;
import com.personal.cl.model.request.ProjectListRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xiaowenrou
 * @date 4/9/22
 */
@Component
@AllArgsConstructor
public class ProjectInfoRepoWrapper {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Flux<ProjectInfoModel> listByCreator(ProjectListRequest request, Pageable pageable) {
        return this.r2dbcEntityTemplate.select(this.buildQuery(request).with(pageable), ProjectInfoModel.class);
    }

    public Mono<Long> countByCreator(ProjectListRequest request) {
        return this.r2dbcEntityTemplate.count(this.buildQuery(request), ProjectInfoModel.class);
    }

    private Query buildQuery(ProjectListRequest request) {
        Criteria criteria = Criteria.empty();
        if (request.projectStatus() != null) {
            criteria = criteria.and(Criteria.where("project_status").is(request.projectStatus()));
        }
        if (StringUtils.hasText(request.projectType())) {
            criteria = criteria.and(Criteria.where("project_type").is(request.projectType()));
        }
        if (request.userId() != null) {
            criteria = criteria.and(Criteria.where("creator").is(request.userId()));
        }
        if (StringUtils.hasText(request.projectName())) {
            criteria = criteria.and(Criteria.where("project_name").like(request.projectName()));
        }
        if (request.fundGreaterThen() != null) {
            criteria = criteria.and(Criteria.where("project_fund").greaterThanOrEquals(request.fundGreaterThen()));
        }
        if (request.fundLessThen() != null) {
            criteria = criteria.and(Criteria.where("project_fund").lessThanOrEquals(request.fundLessThen()));
        }
        return Query.query(criteria);
    }

}
