package com.records.management.config.databaseConfig;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;


/**
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@Configuration
public class PostgreSqlDataBaseConfig {

    private final DatabaseConfig databaseConfig;

    @Autowired
    public PostgreSqlDataBaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseConfig.getJdbcUrl());
        basicDataSource.setUsername(databaseConfig.getUsername());
        basicDataSource.setPassword(databaseConfig.getPassword());
        basicDataSource.setDriverClassName(databaseConfig.getDriver_class_name());
        return basicDataSource;
    }
}
