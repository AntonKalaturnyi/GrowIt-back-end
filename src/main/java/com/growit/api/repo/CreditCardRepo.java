package com.growit.api.repo;

import com.growit.api.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepo extends CrudRepository<CreditCard, Long> {

}
