package com.github.axonmulti.core.event;

import lombok.Value;

import java.util.UUID;

@Value
public class PersonCreatedEvent {

    private final String personId;

    private final String fullName;

}
