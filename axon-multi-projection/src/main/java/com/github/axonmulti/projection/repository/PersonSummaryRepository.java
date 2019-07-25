package com.github.axonmulti.projection.repository;

import com.github.axonmulti.projection.entity.PersonSummary;
import org.springframework.data.repository.CrudRepository;

public interface PersonSummaryRepository extends CrudRepository<PersonSummary, String> {

    Iterable<PersonSummary> findAllByPersonId(String personId);

}
