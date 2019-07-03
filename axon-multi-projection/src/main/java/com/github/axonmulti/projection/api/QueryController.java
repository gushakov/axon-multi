package com.github.axonmulti.projection.api;

import com.github.axonmulti.projection.entity.PersonSummary;
import com.github.axonmulti.projection.repository.PersonSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QueryController {

    private final PersonSummaryRepository personSummaryRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllPersonSummaries")
    public Iterable<PersonSummary> getAllPersonSummaries(){
        return personSummaryRepository.findAll();
    }

}
