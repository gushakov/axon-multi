package com.github.axonmulti.common.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.github.axonmulti.core"})
public class AxonConfig {

    // Copied from excluded org.axonframework.springboot.autoconfig.JpaAutoConfiguration

    @ConditionalOnMissingBean
    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

}
