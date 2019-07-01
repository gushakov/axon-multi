package com.github.axonmulti.saga;

import org.axonframework.boot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.boot.autoconfig.JpaAutoConfiguration;
import org.axonframework.boot.util.RegisterDefaultEntities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// We are excluding default JpaAutoConfiguration, so we need to copy
// the annotation to register default saga entities and the entity manager provider
// from the Axon's auto-configuration.

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.saga"},
        exclude = {ErrorMvcAutoConfiguration.class
                , JpaAutoConfiguration.class
                , JdbcAutoConfiguration.class
        })

@EnableEurekaClient
@RegisterDefaultEntities(packages = {"org.axonframework.eventhandling.saga.repository.jpa"})
public class SagaSpringBootApplication {

        public static void main(String[] args) {
                SpringApplication.run(SagaSpringBootApplication.class, args);
        }

}
