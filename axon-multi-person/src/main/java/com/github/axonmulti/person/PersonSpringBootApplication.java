package com.github.axonmulti.person;

import org.axonframework.boot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.boot.autoconfig.JpaAutoConfiguration;
import org.axonframework.boot.util.RegisterDefaultEntities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// We are not using event sourcing, so do not include repositories for event store and event tracking
@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.person"},
        exclude = {ErrorMvcAutoConfiguration.class
                , JpaAutoConfiguration.class
                , JdbcAutoConfiguration.class
})

@EnableEurekaClient
public class PersonSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonSpringBootApplication.class, args);
    }

}
