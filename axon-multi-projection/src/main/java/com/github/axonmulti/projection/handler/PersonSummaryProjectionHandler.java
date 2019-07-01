package com.github.axonmulti.projection.handler;

import com.github.axonmulti.core.event.PersonCreatedEvent;
import com.github.axonmulti.projection.entity.PersonSummary;
import com.github.axonmulti.projection.repository.PersonSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("person-summary")
public class PersonSummaryProjectionHandler {

    private final PersonSummaryRepository personSummaryRepository;

    @EventHandler
    public void on(PersonCreatedEvent event){
        log.debug("[Projection][Person summary] Record person created event: {}", event);
        personSummaryRepository.save(new PersonSummary(event.getPersonId(),
                event.getFullName()));
    }

}
