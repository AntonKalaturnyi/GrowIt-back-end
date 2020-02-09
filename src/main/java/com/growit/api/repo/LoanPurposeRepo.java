package com.growit.api.repo;

import com.growit.api.domain.LoanPurpose;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPurposeRepo extends CrudRepository<LoanPurpose, Long> {

    LoanPurpose findByPurposeEng(String purpose);

    LoanPurpose findByPurposeUa(String purpose);

}
