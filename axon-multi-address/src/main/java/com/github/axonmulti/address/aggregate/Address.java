package com.github.axonmulti.address.aggregate;

import com.github.axonmulti.core.command.CreateAddressCommand;
import com.github.axonmulti.core.event.AddressCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Aggregate
@Entity
@Data
@Slf4j
@ProcessingGroup("address-aggregate")
public class Address {

    @Id
    @AggregateIdentifier
    private UUID addressId;

    private String streetAndNumber;

    private String zipCode;

    public Address() {
    }

    @CommandHandler
    public Address(CreateAddressCommand command){
        log.debug("[Address][Aggregate][Command] Processing create new address command: {}", command);
        AggregateLifecycle.apply(new AddressCreatedEvent(command.getAddressId(),
                command.getStreetAndNumber(), command.getZipCode()));
    }

    public void on(AddressCreatedEvent event){
        log.debug("[Address][Aggregate][Event] Processing address created event: {}", event);

        this.addressId = event.getAddressId();
        this.streetAndNumber = event.getStreetAndNumber();
        this.zipCode = event.getZipCode();
    }
}
