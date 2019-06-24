package com.github.axonmulti.saga;


import com.github.axonmulti.core.event.NewAddressToPersonAssignmentRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("person-address-saga")
public class CreateNewAddressAndAssignToPersonSaga {

    private final CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "personId")
    private void on(NewAddressToPersonAssignmentRequestedEvent event){
        log.debug("[Saga][Person Address][Start] Started saga on event: {}", event);
    }

}
