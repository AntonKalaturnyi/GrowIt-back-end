package com.growit.api.repo;

import com.growit.api.domain.Investor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepo extends CrudRepository<Investor, Long> {
}

