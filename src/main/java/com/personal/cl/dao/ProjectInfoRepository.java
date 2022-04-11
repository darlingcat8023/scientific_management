package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectInfoModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Repository
public interface ProjectInfoRepository extends R2dbcRepository<ProjectInfoModel, Integer> {

    /**
     * 分页获取
     * @param collection
     * @param pageable
     * @return
     */
    Flux<ProjectInfoModel> findAllByIdIn(Collection<Integer> collection, Pageable pageable);

}
