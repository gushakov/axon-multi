package com.github.axonmulti.common.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.github.axonmulti.core"})
public class CommonAxonConfig {

    // Copied from excluded org.axonframework.springboot.autoconfig.JpaAutoConfiguration

/*
    @ConditionalOnMissingBean
    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }
*/

    // Set all event processors to subscribing mode

    @Autowired
    public void configureEventSubscribers(EventProcessingConfigurer configurer){
        configurer.usingSubscribingEventProcessors();
    }

    // Exchange

    @Bean
    @Qualifier("axonAmqp")
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("axonExchange").build();
    }

    // Binding

    @Bean
    @Qualifier("axonAmqp")
    public Binding binding(@Qualifier("axonAmqp") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange()).with("*").noargs();
    }

    // use Admin to configure at runtime

    @Autowired
    public void configure(AmqpAdmin amqpAdmin,
                          @Qualifier("axonAmqp") Exchange exchange,
                          @Qualifier("axonAmqp") Queue queue,
                          @Qualifier("axonAmqp") Binding binding) {
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

}
