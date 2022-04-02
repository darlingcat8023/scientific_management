package com.personal.cl.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@OpenAPIDefinition(info = @Info(title = "Swagger", version = "1.0", description = "Documentation APIs v1.0"))
public class SwaggerConfiguration {
}
