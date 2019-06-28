package com.github.axonmulti.saga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.saga"},
        exclude = {ErrorMvcAutoConfiguration.class})
@EnableEurekaClient
public class SagaSpringBootApplication {

/*
    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
*/


    public static void main(String[] args) {
        SpringApplication.run(SagaSpringBootApplication.class, args);
    }

}
