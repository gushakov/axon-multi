package com.github.axonmulti.projection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PersonSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projection_generator")
//    @SequenceGenerator(name = "projection_generator", sequenceName = "projection_sequence")
    private Long id;

    private String personId;

    private String addressId;

    private String fullName;

    private String streetAndNumber;

    private String zipCode;

}
