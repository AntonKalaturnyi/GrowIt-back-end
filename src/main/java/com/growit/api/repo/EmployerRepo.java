package com.growit.api.repo;

import com.growit.api.domain.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepo extends CrudRepository<Employer, Long> {
}
