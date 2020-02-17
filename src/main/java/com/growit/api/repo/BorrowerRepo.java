package com.growit.api.repo;

import com.growit.api.domain.Borrower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepo extends CrudRepository<Borrower, Long> {

//    @Query(value = "select * from borrowers where email = ? limit 1", nativeQuery = true)
    Borrower findByEmail(String email);

}
