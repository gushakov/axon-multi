package com.github.axonmulti.core.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreatePersonCommand {

    @TargetAggregateIdentifier
    private final String personId;

    private final String fullName;

}
