package com.github.axonmulti.saga;

import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.saga"},
        exclude = {ErrorMvcAutoConfiguration.class
//                , JpaEventStoreAutoConfiguration.class
//                , JdbcAutoConfiguration.class
//                , JpaAutoConfiguration.class
        })
//@RegisterDefaultEntities(packages = {"org.axonframework.modelling.saga.repository.jpa"})
public class SagaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaSpringBootApplication.class, args);
    }

}
