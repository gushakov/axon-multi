package com.github.axonmulti.projection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class PersonSummary {

    @Id
    private String personId;

    private String addressId;

    private String fullName;

    private String streetAndNumber;

    private String zipCode;

    public PersonSummary(String personId, String fullName) {
        this.personId = personId;
        this.fullName = fullName;
    }
}
