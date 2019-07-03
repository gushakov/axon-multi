package com.github.axonmulti.projection.repository;

import com.github.axonmulti.projection.entity.AddressSummary;
import org.springframework.data.repository.CrudRepository;

public interface AddressSummaryRepository extends CrudRepository<AddressSummary, String> {
}
