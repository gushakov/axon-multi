package com.github.axonmulti.projection.handler;

import com.github.axonmulti.core.event.PrivateAddressAssignedEvent;
import com.github.axonmulti.core.event.PrivateAddressValidatedEvent;
import com.github.axonmulti.core.query.*;
import com.github.axonmulti.projection.entity.PersonSummary;
import com.github.axonmulti.projection.repository.PersonSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("person-summary-projection")
public class PersonSummaryProjectionHandler {

    private final PersonSummaryRepository personSummaryRepository;

    private final QueryGateway queryGateway;

    @EventHandler
    public void on(PrivateAddressValidatedEvent event){
        log.debug("[Projection][Person summary] Record private address validated event: {}", event);

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

                    PersonSummary personSummary = new PersonSummary(null, event.getPersonId(),
                            event.getAddressId(), personResult.getFullName(),
                            addressResult.getStreetAndNumber(),
                            addressResult.getZipCode());
                    personSummaryRepository.save(personSummary);
                    log.debug("[Projection][Person summary] Saved person summary: {}", personSummary);
                });
    }


    @QueryHandler
    public AllAddressesByPersonIdQueryResult handle(AllAddressesByPersonIdQuery query){
        log.debug("[Projection][Person summary] Process query for all assigned addresses: {}", query);

        Iterable<PersonSummary> summaries = personSummaryRepository.findAllByPersonId(query.getPersonId());

        return new AllAddressesByPersonIdQueryResult(query.getPersonId(),
                StreamSupport.stream(summaries.spliterator(), false)
                        .map(PersonSummary::getAddressId).collect(Collectors.toList()));

    }

}
