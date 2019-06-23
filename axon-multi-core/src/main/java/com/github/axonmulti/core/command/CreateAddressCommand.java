package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateAddressCommand {

    @TargetAggregateIdentifier
    private UUID addressId;

    private String streetAndNumber;

    private UUID personId;

}
