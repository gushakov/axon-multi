package com.github.axonmulti.core.event;

import lombok.Value;

@Value
public class PrivateAddressValidationRequestedEvent {

    private final String addressId;

    private final String personId;

}
