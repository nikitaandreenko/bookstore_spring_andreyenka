package com.company;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@Configuration
@ComponentScan
public class AppConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        Properties properties = properties();
        boolean useLocal = properties.getProperty("connection").equals("local");
        config.setDriverClassName(properties.getProperty("db.ClassName"));
        config.setUsername(properties.getProperty(useLocal ? "db.local.user" : "db.elephant.user"));
        config.setPassword(properties.getProperty(useLocal ? "db.local.password" : "db.elephant.password"));
        config.setJdbcUrl(properties.getProperty(useLocal ? "db.local.url" : "db.elephant.url"));
        return new HikariDataSource(config);
    }

    @Bean
    public Properties properties() {

        try {
            Resource resource = new ClassPathResource("/application.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
