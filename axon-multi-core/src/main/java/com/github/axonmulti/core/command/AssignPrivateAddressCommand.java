package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class AssignPrivateAddressCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String addressId;

}
