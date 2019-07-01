package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreatePrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String addressId;

    private final String personId;

    private final String streetAndNumber;

    private final String zipCode;

}
