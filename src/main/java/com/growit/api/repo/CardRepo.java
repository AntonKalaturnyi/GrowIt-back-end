package com.growit.api.repo;

import com.growit.api.domain.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends CrudRepository<Card, Long> {

}
