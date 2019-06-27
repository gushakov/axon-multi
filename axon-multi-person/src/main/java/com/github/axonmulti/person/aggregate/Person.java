package com.github.axonmulti.person.aggregate;


import com.github.axonmulti.core.command.AssignNewAddressToPersonCommand;
import com.github.axonmulti.core.command.CreatePersonCommand;
import com.github.axonmulti.core.event.NewAddressToPersonAssignmentRequestedEvent;
import com.github.axonmulti.core.event.PersonCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("person-aggregate")
public class Person {

    @Id
    @AggregateIdentifier
    private UUID id;

    private String fullName;

    private UUID addressId;

    // do not store this future with the aggregate
    private transient CompletableFuture<UUID> addressAssigned;

    public Person() {
    }

    @CommandHandler
    public Person(CreatePersonCommand command){
        log.debug("[Person][Aggregate][Command] Processing create a new person command: {}", command);
        AggregateLifecycle.apply(new PersonCreatedEvent(command.getPersonId(), command.getFullName()));
    }

    @CommandHandler
    public Future<UUID> handle(AssignNewAddressToPersonCommand command){
        log.debug("[Person][Aggregate][Command] Processing assign new address to a person command: {}", command);
        // should start a saga
        AggregateLifecycle.apply(new NewAddressToPersonAssignmentRequestedEvent(command.getPersonId(),
                command.getStreetAndNumber(), command.getZipCode()));
        addressAssigned = CompletableFuture.completedFuture(UUID.randomUUID());
        return addressAssigned;
    }

    @EventHandler
    public void on(PersonCreatedEvent event){
        log.debug("[Person][Aggregate][Event] Handle person created event: {}", event);
        this.id = event.getPersonId();
        this.fullName = event.getFullName();
    }



}
