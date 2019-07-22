package com.github.axonmulti.address.api;

import com.github.axonmulti.core.command.RequestPrivateAddressValidationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AddressController {

    private final CommandGateway commandGateway;

    @PostMapping("/address/{addressId}/validate")
    public Future<String> validatePrivateAddress(@PathVariable String addressId) {
        log.debug("[Address][API] Request to validate private address, address: {}", addressId);
        return commandGateway.send(new RequestPrivateAddressValidationCommand(addressId));
    }

}
