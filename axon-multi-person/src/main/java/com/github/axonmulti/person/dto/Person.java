package com.github.axonmulti.person.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class Person {

    private UUID personId;

    @NotEmpty
    private String fullName;

}
