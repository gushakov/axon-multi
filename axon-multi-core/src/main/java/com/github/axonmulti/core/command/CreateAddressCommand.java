package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateAddressCommand {

    @TargetAggregateIdentifier
    private final String addressId;

    private final String streetAndNumber;

    private final String zipCode;

}
