package com.growit.api.repo;

import com.growit.api.domain.Investment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepo extends CrudRepository<Investment, Long> {

}
