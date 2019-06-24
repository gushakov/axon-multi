package com.github.axonmulti.core.event;

import lombok.Value;

import java.util.UUID;

@Value
public class PersonCreatedEvent {

    private final UUID personId;

    private final String fullName;

}
