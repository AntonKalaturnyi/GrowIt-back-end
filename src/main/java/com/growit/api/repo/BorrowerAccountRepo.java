package com.growit.api.repo;

import com.growit.api.domain.BorrowerAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerAccountRepo extends CrudRepository<BorrowerAccount, Long> {
}
