package com.github.axonmulti.saga;

import org.axonframework.springboot.autoconfig.AxonServerAutoConfiguration;
import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// We are excluding default JpaAutoConfiguration, so we need to copy the annotation to register saga related entities
// and the declaration of the entity manager provider bean from the Axon's original auto-configuration.

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.saga"},
        exclude = {ErrorMvcAutoConfiguration.class
                , AxonServerAutoConfiguration.class
                , JpaEventStoreAutoConfiguration.class
                , JpaAutoConfiguration.class
                , JdbcAutoConfiguration.class
        })
@EnableEurekaClient
@RegisterDefaultEntities(packages = {"org.axonframework.modelling.saga.repository.jpa"})
public class SagaSpringBootApplication {

        public static void main(String[] args) {
                SpringApplication.run(SagaSpringBootApplication.class, args);
        }

}
