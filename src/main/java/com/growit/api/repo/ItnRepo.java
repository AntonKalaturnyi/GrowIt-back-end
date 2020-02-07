package com.growit.api.repo;

import com.growit.api.domain.ITN;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItnRepo extends CrudRepository<ITN, Long> {

}
