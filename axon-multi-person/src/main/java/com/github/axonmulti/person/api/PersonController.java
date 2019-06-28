package com.github.axonmulti.person.api;

import com.github.axonmulti.core.command.AssignNewAddressToPersonCommand;
import com.github.axonmulti.core.command.CreatePersonCommand;
import com.github.axonmulti.person.dto.PersonDto;
import com.github.axonmulti.person.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final CommandGateway commandGateway;

    @PostMapping("/persons")
    public Future<UUID> createPerson(@RequestBody @Valid PersonDto personDto) {
        log.debug("[Person][API] Creating a person: {}", personDto);
        return commandGateway.send(new CreatePersonCommand(personDto.getPersonId(), personDto.getFullName()));
    }
    @PostMapping("/person/{personId}/address")
    public Future<String> addNewAddressToPerson(@RequestParam String personId, @RequestBody @Valid AddressDto dto) {
        log.debug("[Person][API] Processing request to add a new address to a person: {}, address: {}", personId, dto);
        return commandGateway.send(new AssignNewAddressToPersonCommand(personId, dto.getStreetAndNumber(), dto.getZipCode()
        ));
    }

}
