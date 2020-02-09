package com.growit.api.repo;

import com.growit.api.domain.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanStatusRepo extends CrudRepository<LoanStatus, Long> {

    LoanStatus findByStatusEng(String status);

    LoanStatus findByStatusUa(String status);

}
