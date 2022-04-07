package com.personal.cl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "com.personal.cl.dao")
@Configuration(proxyBeanMethods = false)
public class DatabaseConfiguration {

    /**
     * 命名转化策略
     * @return
     */
    @Bean
    public NamingStrategy getNamingStrategy() {
        return new NamingStrategy() {};
    }

}
