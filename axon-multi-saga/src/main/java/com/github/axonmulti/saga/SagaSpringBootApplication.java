package com.github.axonmulti.saga;

import org.axonframework.boot.autoconfig.AMQPAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.saga"},
        exclude = {ErrorMvcAutoConfiguration.class, AMQPAutoConfiguration.class})
@EnableEurekaClient
public class SagaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaSpringBootApplication.class, args);
    }

}
