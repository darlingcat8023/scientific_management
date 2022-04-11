package com.personal.cl.service;

import com.personal.cl.dao.ProjectFileRepository;
import com.personal.cl.dao.ProjectInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProjectFileService {

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectFileRepository projectFileRepository;

    public Mono<String> uploadFile(Integer projectId, Mono<Part> requestMono) {
        return Mono.just("success");
    }
}
