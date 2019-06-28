package com.github.axonmulti.person.saga;


import com.github.axonmulti.core.command.AssignPrivateAddressCommand;
import com.github.axonmulti.core.command.CreatePrivateAddressCommand;
import com.github.axonmulti.core.event.PrivateAddressAssignedEvent;
import com.github.axonmulti.core.event.PrivateAddressAssignmentRequestedEvent;
import com.github.axonmulti.core.event.PrivateAddressCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@ProcessingGroup("assign-private-address-saga")
@Slf4j
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
    }

    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressCreatedEvent event){
        log.debug("[Saga][Person Address] Private address was created: {}", event);

        // assign created address to a person
        commandGateway.send(new AssignPrivateAddressCommand(event.getPersonId(), event.getAddressId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressAssignedEvent event){
        log.debug("[Saga][Person Address][End] Private address was assigned: {}", event);
    }

}
