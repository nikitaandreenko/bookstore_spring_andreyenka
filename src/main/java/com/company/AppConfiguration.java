package com.company;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class AppConfiguration {

    @Bean
    public EntityManagerFactory factory(){
        return Persistence.createEntityManagerFactory("psql");
    }

    @Bean
    public EntityManager entityManager(){
        return factory().createEntityManager();
    }

}
