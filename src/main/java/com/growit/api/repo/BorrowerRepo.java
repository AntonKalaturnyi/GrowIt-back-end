package com.growit.api.repo;

import com.growit.api.domain.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepo extends CrudRepository<Borrower, Long> {
}
