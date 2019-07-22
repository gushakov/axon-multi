package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RejectPrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String addressId;

}
