package com.github.axonmulti.person.api;

import com.github.axonmulti.core.command.CreatePersonCommand;
import com.github.axonmulti.person.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final CommandGateway commandGateway;

    private final EventBus eventBus;

    @PostMapping("/persons")
    public Future<UUID> createPerson(@RequestBody @Valid PersonDto personDto) {
        log.debug("[Person][API] Creating a person: {}", personDto);
        return commandGateway.send(new CreatePersonCommand(personDto.getPersonId(), personDto.getFullName()));
    }

}
