package com.github.axonmulti.projection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AddressSummary {

    @Id
    private String addressId;

    private String streetAndNumber;

    private String zipCode;

}
