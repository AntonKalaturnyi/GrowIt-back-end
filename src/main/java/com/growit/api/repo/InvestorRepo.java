package com.growit.api.repo;

import com.growit.api.domain.Investor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepo extends CrudRepository<Investor, Long> {

//    @Query(value = "select * from investors where email = ? limit 1", nativeQuery = true)
    Investor findByEmail(String email);

}

