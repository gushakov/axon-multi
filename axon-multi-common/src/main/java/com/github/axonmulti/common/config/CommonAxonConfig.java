package com.github.axonmulti.common.config;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.github.axonmulti.core"})
public class CommonAxonConfig {


    // Store all events in the memory, overrides default store from
    // org.axonframework.springboot.autoconfig.AxonServerAutoConfiguration

    @Bean
    public EventStore eventStore(){
        return EmbeddedEventStore.builder()
                .storageEngine(new InMemoryEventStorageEngine())
                .build();
    }

    // Set all event processors to subscribing mode, we are not
    // using event sourcing

    @Autowired
    public void configureEventSubscribers(EventProcessingConfigurer configurer) {
        configurer.usingSubscribingEventProcessors();
    }

    // Common RabbitMQ infrastructure, each module will declare its own queue
    // for each subscribing processor group

    @Bean
    @Qualifier("axonAmqp")
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("axonExchange").build();
    }

    @Bean
    @Qualifier("axonAmqp")
    public Binding binding(@Qualifier("axonAmqp") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange()).with("*").noargs();
    }

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
