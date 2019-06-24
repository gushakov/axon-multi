package com.github.axonmulti.core.event;

import lombok.Value;

import java.util.UUID;

@Value
public class NewAddressToPersonAssignmentRequestedEvent {

    private final UUID personId;

    private final String streetAndNumber;

    private final String zipCode;

}
