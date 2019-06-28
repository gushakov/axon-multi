package com.github.axonmulti.person.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class AddressDto {

    @NotNull
    private final String streetAndNumber;

    @NotNull
    private final String zipCode;

}
