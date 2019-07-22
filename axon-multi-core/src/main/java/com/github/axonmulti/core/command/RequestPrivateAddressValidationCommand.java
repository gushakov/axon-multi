package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RequestPrivateAddressValidationCommand {

    @TargetAggregateIdentifier
    private final String addressId;

}
