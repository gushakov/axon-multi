package com.github.axonmulti.core.event;

import lombok.Value;

@Value
public class PrivateAddressAssignmentRequestedEvent {

    private final String addressId;

    private final String personId;

    private final String streetAndNumber;

    private final String zipCode;

}
