package com.github.axonmulti.core.command;

import lombok.Value;

@Value
public class ValidatePrivateAddressCommand {

    private final String addressId;

}
