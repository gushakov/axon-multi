package com.github.axonmulti.person.config;

import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Scan the core artifacts: commands, events, queries from a base package
// in the core module
@Configuration
@ComponentScan(basePackages = {"com.github.axonmulti.core.aggregate"})
public class AxonConfig {


    // Simple event bus
    // TODO: replace with distributed event bus via Eureka

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }


    // This is not needed in 4.1.1
    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

}
