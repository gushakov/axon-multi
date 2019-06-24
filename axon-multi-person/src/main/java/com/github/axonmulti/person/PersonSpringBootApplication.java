package com.github.axonmulti.person;

import org.axonframework.boot.autoconfig.AMQPAutoConfiguration;
import org.axonframework.boot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.boot.autoconfig.JpaAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// We are not using event sourcing, so exclude event persistence configuration.
// Also there is a problem with imports in org.axonframework.boot.autoconfig.AMQPAutoConfiguration
// from axon-sring-boot-autoconfigure 4.0-M2, should be fixed when all the versions are aligned for 4.1.
// We are using local override: com.github.axonmulti.common.config.AmqpAutoConfiguration, for now.
@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.person"},
        exclude = {ErrorMvcAutoConfiguration.class,
                JpaAutoConfiguration.class,
                JdbcAutoConfiguration.class,
                AMQPAutoConfiguration.class
        })
@EnableEurekaClient
public class PersonSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonSpringBootApplication.class, args);
    }

}
