package com.github.axonmulti.core.query;

import lombok.Value;

@Value
public class PersonByIdQueryResult {

    private String personId;

    private String addressId;

    private String fullName;

}
