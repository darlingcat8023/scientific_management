package com.personal.cl.model.request;

import javax.validation.constraints.NotNull;

import static com.personal.cl.model.RequestVerify.*;

public record ProjectCommitRequest (
        @NotNull(message = "项目id不能为空", groups = {ProjectCommitVerify.class})
        Integer projectId
) {}
