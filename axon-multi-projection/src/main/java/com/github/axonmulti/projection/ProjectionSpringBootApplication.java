package com.github.axonmulti.projection;

import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.projection"},
        exclude = {ErrorMvcAutoConfiguration.class
                , JpaEventStoreAutoConfiguration.class
                , JpaAutoConfiguration.class
                , JdbcAutoConfiguration.class
        })
@EnableJpaRepositories
public class ProjectionSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectionSpringBootApplication.class, args);
    }

}
