package com.github.axonmulti.address;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.boot.autoconfig.AMQPAutoConfiguration;
import org.axonframework.boot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.boot.autoconfig.JpaAutoConfiguration;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.address"},
        exclude = {ErrorMvcAutoConfiguration.class,
                JpaAutoConfiguration.class,
                JdbcAutoConfiguration.class,
                AMQPAutoConfiguration.class
        })
@EnableEurekaClient
@Slf4j
public class AddressSpringBootApplication implements CommandLineRunner {

    @Autowired
    private CommandGateway commandGateway;

    public static void main(String[] args) {
        SpringApplication.run(AddressSpringBootApplication.class, args);
    }


    public void run(String... args) throws Exception {
        log.debug("[Application] Configured distributed command bus: {}", commandGateway);
    }
}
