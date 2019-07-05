package com.github.axonmulti.address.aggregate;

import com.github.axonmulti.core.command.CreatePrivateAddressCommand;
import com.github.axonmulti.core.command.ValidatePrivateAddressCommand;
import com.github.axonmulti.core.event.PrivateAddressCreatedEvent;
import com.github.axonmulti.core.event.PrivateAddressValidatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("address-aggregate")
public class Address {

    enum ValidationStatus {
        Initial,
        Validated,
        ToBeDeleted
    }

    @Id
    @AggregateIdentifier
    private String addressId;

    @Column(nullable = false)
    private String personId;

    private String streetAndNumber;

    private String zipCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ValidationStatus validationStatus;

    public Address() {
    }

    @CommandHandler
    public Address(CreatePrivateAddressCommand command) {
        log.debug("[Address][Aggregate][Command] Processing create new private address command: {}", command);
        AggregateLifecycle.apply(new PrivateAddressCreatedEvent(command.getAddressId(), command.getPersonId(),
                command.getStreetAndNumber(), command.getZipCode()));
    }

    @CommandHandler
    public void handle(ValidatePrivateAddressCommand command){
        log.debug("[Address][Aggregate][Command] Processing validate private address command: {}", command);

        // maybe some validation logic here
        AggregateLifecycle.apply(new PrivateAddressValidatedEvent(command.getAddressId(), this.addressId));
    }

    @EventHandler
    public void on(PrivateAddressCreatedEvent event) {
        log.debug("[Address][Aggregate][Event] Processing new private address created event: {}", event);
        this.addressId = event.getAddressId();
        this.personId = event.getPersonId();
        this.streetAndNumber = event.getStreetAndNumber();
        this.zipCode = event.getZipCode();
        this.validationStatus = ValidationStatus.Initial;
    }

    @EventHandler
    public void on(PrivateAddressValidatedEvent event){
        log.debug("[Address][Aggregate][Event] Processing validate address event: {}", event);
        this.validationStatus = ValidationStatus.Validated;
    }
}
