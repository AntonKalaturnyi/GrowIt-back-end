package com.growit.api.repo;

import com.growit.api.domain.Passport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepo extends CrudRepository<Passport, Long> {
}
