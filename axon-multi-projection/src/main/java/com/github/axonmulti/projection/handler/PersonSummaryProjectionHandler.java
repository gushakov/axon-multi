package com.github.axonmulti.projection.handler;

import com.github.axonmulti.core.event.PersonCreatedEvent;
import com.github.axonmulti.core.event.PrivateAddressAssignedEvent;
import com.github.axonmulti.core.query.AddressByIdQuery;
import com.github.axonmulti.projection.entity.AddressSummary;
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
    public void on(PersonCreatedEvent event){
        log.debug("[Projection][Person summary] Record person created event: {}", event);
        personSummaryRepository.save(new PersonSummary(event.getPersonId(),
                event.getFullName()));
    }

    @EventHandler
    public void on(PrivateAddressAssignedEvent event){
        log.debug("[Projection][Person summary] Record private address assigned event: {}", event);

        // this asynchronous, parallel, reactive code

        // find person summary
        Mono<PersonSummary> findPersonSummaryMono = Mono.fromSupplier(() ->
                personSummaryRepository.findById(event.getPersonId())
                        .orElseThrow(IllegalStateException::new));

        // query for address summary
        Mono<AddressSummary> findAddressSummaryMono = Mono.fromFuture(queryGateway
                .query(new AddressByIdQuery(event.getAddressId()), AddressSummary.class));

        // wait for both summaries then update person summary
        Flux.zip(findPersonSummaryMono, findAddressSummaryMono)
                .subscribe(tuple -> {
                    PersonSummary personSummary = tuple.getT1();
                    AddressSummary addressSummary = tuple.getT2();
                    personSummary.setAddressId(event.getAddressId());
                    personSummary.setStreetAndNumber(addressSummary.getStreetAndNumber());
                    personSummary.setZipCode(addressSummary.getZipCode());
                    personSummaryRepository.save(personSummary);
                    log.debug("[Projection][Person summary] Updated person summary: {}", personSummary);
                });

    }

}
