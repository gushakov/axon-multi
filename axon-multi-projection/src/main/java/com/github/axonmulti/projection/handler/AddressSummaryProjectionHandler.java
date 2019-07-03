package com.github.axonmulti.projection.handler;

import com.github.axonmulti.core.event.PrivateAddressCreatedEvent;
import com.github.axonmulti.core.query.AddressByIdQuery;
import com.github.axonmulti.projection.entity.AddressSummary;
import com.github.axonmulti.projection.repository.AddressSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("address-summary-projection")
@RequiredArgsConstructor
@Slf4j
public class AddressSummaryProjectionHandler {

    private final AddressSummaryRepository addressSummaryRepository;

    @EventHandler
    public void on(PrivateAddressCreatedEvent event){
        log.debug("[Projection][Address summary] Record private address created event: {}", event);
        addressSummaryRepository.save(new AddressSummary(event.getAddressId(),
                event.getStreetAndNumber(), event.getZipCode()));
    }

    @QueryHandler
    private AddressSummary handle(AddressByIdQuery query){
        log.debug("[Projection][Query] Received query: {}", query);
        return addressSummaryRepository.findById(query.getAddressId())
                .orElseThrow(IllegalStateException::new);
    }

}
