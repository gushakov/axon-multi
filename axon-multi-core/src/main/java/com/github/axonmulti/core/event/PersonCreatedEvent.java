package com.github.axonmulti.core.event;

import lombok.Value;

import java.util.UUID;

@Value
public class PersonCreatedEvent {

    private UUID personId;

    private String fullName;

}
