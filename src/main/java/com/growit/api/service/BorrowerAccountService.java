package com.growit.api.service;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.CreditHistory;
import com.growit.api.domain.Loan;
import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.dto.BorrowerAccountLoanDto;
import com.growit.api.dto.DashboardLoanDto;
import com.growit.api.mapper.BorrowerAccountMapper;
import com.growit.api.repo.BorrowerAccountRepo;
import com.growit.api.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
        return borrower.isVerified() ? new BorrowerAccountDto(
                borrower.getBorrowerAccount().getAvailableBalance(),
                borrower.getDailyLoanRate(),
                borrower.getSafetyRank(),
                borrower.getVerificationScore(),
                latestLoan.getStatus().name()
        ) : new BorrowerAccountDto();
    }


    @PreAuthorize("hasAuthority('BORROWER_ON_CHECK')")
    public List<BorrowerAccountLoanDto> getPreviousLoans(Borrower borrower) {
        ArrayList<BorrowerAccountLoanDto> list = new ArrayList<>();
        ArrayList<Loan> dbList = (ArrayList) loanRepo.findByBorrowerAndLatestFalseOrderByCloseDateDesc(borrower);
        for (Loan loan : dbList) {
            list.add(new BorrowerAccountLoanDto(
                    loan.getStatus().name(),
                    loan.getAmountApproved(),
                    loan.getTerm(),
                    loan.getDailyLoanRate(),
                    loan.getSafetyRank() + "-" + loan.getVerificationScore(),
                    loan.getLoanPurpose().getPurposeEng(),
                    loan.getCloseDate().toLocalDate(),
                    loan.getRatingChange() > 0 ? "+" + loan.getRatingChange() : loan.getRatingChange() + ""
            ));
        }
        return list;
    }

/*
    public BorrowerAccountDto update(BorrowerAccountDto dto) {
        dto.setCreated(borrowerAccountRepo.findById(dto.getId()).get().getCreated());
        return mapper.toDto(borrowerAccountRepo.save(mapper.toEntity(dto)) );
    }*/

}
