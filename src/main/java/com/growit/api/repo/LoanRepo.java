package com.growit.api.repo;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.Loan;
import com.growit.api.domain.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepo extends CrudRepository<Loan, Long> {

    Loan findByBorrowerAndLatestTrue(Borrower borrower);

    Loan findByBorrowerAndLatestTrueAndStatus(Borrower borrower, LoanStatus status);

    List<Loan> findByBorrowerAndLatestFalseOrderByCloseDateDesc(Borrower borrower);

}
