package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class RequestPrivateAddressAssignmentCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String streetAndNumber;

    private final String zipCode;

}
