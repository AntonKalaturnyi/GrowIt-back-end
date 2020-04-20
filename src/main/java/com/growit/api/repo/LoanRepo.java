package com.growit.api.repo;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends CrudRepository<Loan, Long> {

    Loan findByBorrowerAndLatestTrue(Borrower borrower);

}
