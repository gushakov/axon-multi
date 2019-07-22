package com.github.axonmulti.core.query;

import lombok.Value;

import java.util.List;

@Value
public class AllAddressesByPersonIdQueryResult {

    private final String personId;

    private final List<String> addressesIds;

}
