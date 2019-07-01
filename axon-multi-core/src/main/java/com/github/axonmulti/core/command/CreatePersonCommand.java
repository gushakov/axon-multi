package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreatePersonCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String fullName;

}
