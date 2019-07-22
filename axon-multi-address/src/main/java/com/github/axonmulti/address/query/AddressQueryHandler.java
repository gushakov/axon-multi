package com.github.axonmulti.address.query;

import com.github.axonmulti.address.aggregate.Address;
import com.github.axonmulti.core.query.AddressByIdQuery;
import com.github.axonmulti.core.query.AddressByIdQueryResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddressQueryHandler {

    private final EntityManager entityManager;

    @QueryHandler
    public AddressByIdQueryResult handle(AddressByIdQuery query) {
        Address address = entityManager.find(Address.class, query.getAddressId());
        return new AddressByIdQueryResult(address.getAddressId(),
                address.getPersonId(), address.getStreetAndNumber(), address.getZipCode());
    }

}
