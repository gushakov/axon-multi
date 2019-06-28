package com.github.axonmulti.person.aggregate;


import com.github.axonmulti.core.command.AssignPrivateAddressCommand;
import com.github.axonmulti.core.command.CreatePersonCommand;
import com.github.axonmulti.core.command.RequestPrivateAddressAssignmentCommand;
import com.github.axonmulti.core.event.PersonCreatedEvent;
import com.github.axonmulti.core.event.PrivateAddressAssignmentRequestedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("person-aggregate")
public class Person {

    @Id
    @AggregateIdentifier
    private String id;

    private String fullName;

    private String addressId;


    public Person() {
    }

    @CommandHandler
    public Person(CreatePersonCommand command){
        log.debug("[Person][Aggregate][Command] Creating new person: {}", command);
        apply(new PersonCreatedEvent(command.getPersonId(), command.getFullName()));
    }

    @CommandHandler
    public void handle(RequestPrivateAddressAssignmentCommand command){
        log.debug("[Person][Aggregate][Command] Private address assignment requested: {}", command);
        // should start a saga
        apply(new PrivateAddressAssignmentRequestedEvent(UUID.randomUUID().toString(),
                command.getPersonId(),
                command.getStreetAndNumber(),
                command.getZipCode()));

    }

    @EventHandler
    public void on(PersonCreatedEvent event){
        log.debug("[Person][Aggregate][Event] Handle person created event: {}", event);
        this.id = event.getPersonId();
        this.fullName = event.getFullName();
    }



}
