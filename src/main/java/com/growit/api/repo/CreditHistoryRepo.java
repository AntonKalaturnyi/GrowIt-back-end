package com.growit.api.repo;

import com.growit.api.domain.CreditHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditHistoryRepo extends CrudRepository<CreditHistory, Long> {

}
