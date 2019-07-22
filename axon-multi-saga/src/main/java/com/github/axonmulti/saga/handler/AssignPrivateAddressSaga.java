package com.github.axonmulti.saga.handler;


import com.github.axonmulti.core.command.*;
import com.github.axonmulti.core.event.*;
import com.github.axonmulti.core.query.AllAddressesByPersonIdQuery;
import com.github.axonmulti.core.query.AllAddressesByPersonIdQueryResult;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
@ProcessingGroup("private-address-saga")
public class AssignPrivateAddressSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

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
    public void on(PrivateAddressValidationRequestedEvent event) {
        log.debug("[Saga][Person Address] Received request for private address validation: {}", event);

        // validate address if no more than 2 addresses have already been assigned to this person,
        // otherwise reject this address assigment
        queryGateway.query(new AllAddressesByPersonIdQuery(event.getPersonId()),
                AllAddressesByPersonIdQueryResult.class)
                .thenAccept(queryResult -> {
                    if (queryResult.getAddressesIds().size() <= 2) {
                        commandGateway.send(new ValidatePrivateAddressCommand(event.getAddressId()));
                    } else {
                        commandGateway.send(new RejectPrivateAddressCommand(event.getAddressId()));
                    }
                });
    }

    @SagaEventHandler(associationProperty = "personId")
    public void on(PrivateAddressValidatedEvent event) {
        log.debug("[Saga][Person Address] Private address was validated: {}", event);

        // assign created address to a person
        commandGateway.send(new AssignPrivateAddressCommand(event.getPersonId(), event.getAddressId()));
    }

    @SagaEventHandler(associationProperty = "personId")
    @EndSaga
    public void on(PrivateAddressAssignedEvent event) {
        log.debug("[Saga][Person Address][End] Private address was assigned: {}", event);
        // end saga
    }

    @SagaEventHandler(associationProperty = "personId")
    @EndSaga
    public void on(PrivateAddressRejectedEvent event){
        log.debug("[Saga][Person Address][End] Private address was rejected: {}", event);
        // end saga
    }

}
