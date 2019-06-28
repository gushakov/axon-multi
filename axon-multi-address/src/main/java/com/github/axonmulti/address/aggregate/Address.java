package com.github.axonmulti.address.aggregate;

import com.github.axonmulti.core.command.CreatePrivateAddressCommand;
import com.github.axonmulti.core.event.PrivateAddressCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("address-aggregate")
public class Address {

    @Id
    @AggregateIdentifier
    private String addressId;

    private String personId;

    private String streetAndNumber;

    private String zipCode;

    public Address() {
    }

    @CommandHandler
    public Address(CreatePrivateAddressCommand command){
        log.debug("[Address][Aggregate][Command] Processing create new private address command: {}", command);
        AggregateLifecycle.apply(new PrivateAddressCreatedEvent(command.getAddressId(), command.getPersonId(),
                command.getStreetAndNumber(), command.getZipCode()));
    }

    public void on(PrivateAddressCreatedEvent event){
        log.debug("[Address][Aggregate][Event] Processing new private address created event: {}", event);
        this.addressId = event.getAddressId();
        this.streetAndNumber = event.getStreetAndNumber();
        this.zipCode = event.getZipCode();
    }
}
