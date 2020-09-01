package com.records.management.config.databaseConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driver_class_name;

    public DatabaseConfig() {
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver_class_name() {
        return driver_class_name;
    }

    public void setDriver_class_name(String driver_class_name) {
        this.driver_class_name = driver_class_name;
    }
}
