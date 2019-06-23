package com.github.axonmulti.person.command;


import com.github.axonmulti.core.command.CreatePersonCommand;
import com.github.axonmulti.core.event.PersonCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Aggregate
@Entity
@Data
@Slf4j
public class Person {

    @Id
    @AggregateIdentifier
    private UUID id;

    private String fullName;

    public Person() {
    }

    @CommandHandler
    public Person(CreatePersonCommand command){
        log.debug("[Command] Processing create a new person command: {}", command);
        AggregateLifecycle.apply(new PersonCreatedEvent(command.getPersonId(), command.getFullName()));
    }

    @EventHandler
    public void on(PersonCreatedEvent event){
        log.debug("[Event] Handle person created event: {}", event);
        this.id = event.getPersonId();
        this.fullName = event.getFullName();
    }
}
