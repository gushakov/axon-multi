package com.github.axonmulti.saga.handler;


import com.github.axonmulti.core.command.AssignPrivateAddressCommand;
import com.github.axonmulti.core.command.CreatePrivateAddressCommand;
import com.github.axonmulti.core.event.PrivateAddressAssignedEvent;
import com.github.axonmulti.core.event.PrivateAddressAssignmentRequestedEvent;
import com.github.axonmulti.core.event.PrivateAddressValidatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
@ProcessingGroup("private-address-saga")
public class AssignPrivateAddressSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    public AssignPrivateAddressSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressAssignmentRequestedEvent event) {
        log.debug("[Saga][Person Address][Start] Started saga on event: {}", event);

        // create new private address
        commandGateway.send(new CreatePrivateAddressCommand(event.getAddressId(),
                event.getPersonId(), event.getStreetAndNumber(), event.getZipCode()));

        // and wait for validation
    }

    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressValidatedEvent event) {
        log.debug("[Saga][Person Address] Private address was validated: {}", event);

        // assign created address to a person
        commandGateway.send(new AssignPrivateAddressCommand(event.getPersonId(), event.getAddressId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressAssignedEvent event) {
        log.debug("[Saga][Person Address][End] Private address was assigned: {}", event);
        // end saga
    }

}
