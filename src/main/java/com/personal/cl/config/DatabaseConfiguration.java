package com.personal.cl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
public class DatabaseConfiguration {
}
