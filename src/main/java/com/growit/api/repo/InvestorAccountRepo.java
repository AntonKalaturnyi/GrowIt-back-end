package com.growit.api.repo;

import com.growit.api.domain.InvestorAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorAccountRepo extends CrudRepository<InvestorAccount, Long> {
}
