package com.personal.cl.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "com.personal.cl.dao")
@Configuration(proxyBeanMethods = false)
public class DatabaseConfiguration {

    /**
     * 响应式事务管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    /**
     * 命名转化策略
     * @return
     */
    @Bean
    public NamingStrategy getNamingStrategy() {
        return new NamingStrategy() {};
    }

}
