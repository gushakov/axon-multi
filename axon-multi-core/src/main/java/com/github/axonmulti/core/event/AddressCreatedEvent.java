package com.github.axonmulti.core.event;

import lombok.Value;

import java.util.UUID;

@Value
public class AddressCreatedEvent {

    private final String addressId;

    private final String streetAndNumber;

    private final String zipCode;

}
