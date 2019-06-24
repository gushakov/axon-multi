package com.github.axonmulti.address.query;

import com.github.axonmulti.core.event.PersonCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

//TODO: this should be a Saga

@Component
@ProcessingGroup("address-person-association-handler")
@Slf4j
public class AddressPersonAssociationHandler {

    @EventHandler
    public void on(PersonCreatedEvent event) {
        log.debug("[Address-Person association handler] Received person created event: {}", event);
    }

}
