package com.growit.api.service;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.Loan;
import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.mapper.BorrowerAccountMapper;
import com.growit.api.repo.BorrowerAccountRepo;
import com.growit.api.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BorrowerAccountService {

    private final BorrowerAccountMapper mapper;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final LoanRepo loanRepo;

    @Autowired
    public BorrowerAccountService(BorrowerAccountMapper mapper, BorrowerAccountRepo borrowerAccountRepo, LoanRepo loanRepo) {
        this.mapper = mapper;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.loanRepo = loanRepo;
    }

    @PreAuthorize("hasAuthority('BORROWER_ON_CHECK')") // to be changed: to VERIFIED_BORROWER when verification implemented
    public BorrowerAccountDto getAccountData(Borrower borrower) {
        Loan latestLoan = loanRepo.findByBorrowerAndLatestTrue(borrower);
        return new BorrowerAccountDto(
                borrower.getBorrowerAccount().getAvailableBalance(),
                borrower.getDailyLoanRate(),
                borrower.getSafetyRank(),
                borrower.getVerificationScore(),
                latestLoan.getStatus().name()
        );
    }

/*
    public BorrowerAccountDto update(BorrowerAccountDto dto) {
        dto.setCreated(borrowerAccountRepo.findById(dto.getId()).get().getCreated());
        return mapper.toDto(borrowerAccountRepo.save(mapper.toEntity(dto)) );
    }*/

}
