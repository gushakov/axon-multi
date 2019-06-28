package com.github.axonmulti.core.event;

import lombok.Value;

@Value
public class PrivateAddressAssignedEvent {

    private final String personId;

    private final String addressId;

}
