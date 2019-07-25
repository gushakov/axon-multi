package com.github.axonmulti.person.api;

import com.github.axonmulti.core.command.CreatePersonCommand;
import com.github.axonmulti.core.command.RequestPrivateAddressAssignmentCommand;
import com.github.axonmulti.person.dto.AddressDto;
import com.github.axonmulti.person.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final CommandGateway commandGateway;

    @PostMapping("/persons")
    public Future<String> createPerson(@RequestBody @Valid PersonDto personDto) {
        log.debug("[Person][API] Creating a person: {}", personDto);
        return commandGateway.send(new CreatePersonCommand(UUID.randomUUID().toString(), personDto.getFullName()));
    }

    @PostMapping("/person/{personId}/address")
    public Future<String> assignPrivateAddress(@PathVariable String personId, @RequestBody @Valid AddressDto dto) {
        log.debug("[Person][API] Request to assign new private address, person: {}, address: {}", personId, dto);
        return commandGateway.send(new RequestPrivateAddressAssignmentCommand(personId,
                dto.getStreetAndNumber(),
                dto.getZipCode()));
    }

}
