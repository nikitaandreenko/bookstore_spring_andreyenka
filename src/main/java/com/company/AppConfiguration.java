package com.company;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
public class AppConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate (){
        return new JdbcTemplate(null);//FIXME add datasource
    }

}
