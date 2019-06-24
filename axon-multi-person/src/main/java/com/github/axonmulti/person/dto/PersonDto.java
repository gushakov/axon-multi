package com.github.axonmulti.person.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class PersonDto {

    @NotNull
    private final UUID personId;

    @NotNull
    private final String fullName;

}
