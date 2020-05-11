package com.growit.api.repo;

import com.growit.api.domain.Investment;
import com.growit.api.domain.Investor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepo extends CrudRepository<Investment, Long> {

    List<Investment> findAllByInvestor(Investor inv);

}
