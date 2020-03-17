package com.growit.api.repo;

import com.growit.api.domain.Employment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepo extends CrudRepository<Employment, Long> {
}
