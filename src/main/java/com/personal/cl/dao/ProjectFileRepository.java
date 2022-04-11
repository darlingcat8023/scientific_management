package com.personal.cl.dao;

import com.personal.cl.dao.model.ProjectFileModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProjectFileRepository extends R2dbcRepository<ProjectFileModel, Integer> {

    /**
     * 根据项目id查找
     * @param projectId
     * @return
     */
    Flux<ProjectFileModel> findProjectFileModelsByProjectId(Integer projectId);

}
