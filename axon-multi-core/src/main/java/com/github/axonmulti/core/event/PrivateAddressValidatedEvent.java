package com.github.axonmulti.core.event;

import lombok.Value;

@Value
public class PrivateAddressValidatedEvent {

    private final String addressId;

    // need person ID for saga association

    private final String personId;

}
