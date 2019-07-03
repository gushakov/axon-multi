package com.github.axonmulti.projection.handler;

import com.github.axonmulti.core.event.PrivateAddressAssignedEvent;
import com.github.axonmulti.core.query.AddressByIdQuery;
import com.github.axonmulti.core.query.AddressByIdQueryResult;
import com.github.axonmulti.core.query.PersonByIdQuery;
import com.github.axonmulti.core.query.PersonByIdQueryResult;
import com.github.axonmulti.projection.entity.PersonSummary;
import com.github.axonmulti.projection.repository.PersonSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("person-summary-projection")
public class PersonSummaryProjectionHandler {

    private final PersonSummaryRepository personSummaryRepository;

    private final QueryGateway queryGateway;

    @EventHandler
    public void on(PrivateAddressAssignedEvent event){
        log.debug("[Projection][Person summary] Record private address assigned event: {}", event);

        // query for the person with the matching ID
        Mono<PersonByIdQueryResult> findPersonMono = Mono.fromFuture(queryGateway
                .query(new PersonByIdQuery(event.getPersonId()), PersonByIdQueryResult.class));

        // query for the address with the matching ID
        Mono<AddressByIdQueryResult> findAddressMono = Mono.fromFuture(queryGateway
                .query(new AddressByIdQuery(event.getAddressId()), AddressByIdQueryResult.class));

        // execute both queries in parallel and combine the results
        Flux.zip(findPersonMono, findAddressMono)
                .subscribe(tuple -> {
                    PersonByIdQueryResult personResult = tuple.getT1();
                    AddressByIdQueryResult addressResult = tuple.getT2();

                    PersonSummary personSummary = new PersonSummary(event.getPersonId(),
                            event.getAddressId(), personResult.getFullName(),
                            addressResult.getStreetAndNumber(),
                            addressResult.getZipCode());

                    personSummaryRepository.save(personSummary);
                    log.debug("[Projection][Person summary] Saved person summary: {}", personSummary);
                });
    }

}
