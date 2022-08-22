package com.company;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
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
        String url_key = null;
        String user_key = null;
        String password_key = null;
        String typeOfConnection = ConfigurationManager.INSTANCE.getProperty("connection");
        String className = ConfigurationManager.INSTANCE.getProperty("db.ClassName");

        if(typeOfConnection.equals("1")){

            url_key = ConfigurationManager.INSTANCE.getProperty("db.local.url");
            user_key = ConfigurationManager.INSTANCE.getProperty("db.local.user");
            password_key = ConfigurationManager.INSTANCE.getProperty("db.local.password");
        }
        else if(typeOfConnection.equals("2")){
            url_key = ConfigurationManager.INSTANCE.getProperty("db.elephant.url");
            user_key = ConfigurationManager.INSTANCE.getProperty("db.elephant.user");
            password_key = ConfigurationManager.INSTANCE.getProperty("db.elephant.password");
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(className);
        config.setUsername(user_key);
        config.setPassword(password_key);
        config.setJdbcUrl(url_key);
        return new HikariDataSource(config);
    }
}
