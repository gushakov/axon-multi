package com.github.axonmulti.saga;


import com.github.axonmulti.core.event.NewAddressToPersonAssignmentRequestedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
@ProcessingGroup("person-address-saga")
public class CreateNewAddressAndAssignToPersonSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    public CreateNewAddressAndAssignToPersonSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "personId")
    public void on(NewAddressToPersonAssignmentRequestedEvent event) {
        log.debug("[Saga][Person Address][Start] Started saga on event: {}", event);
    }

}
