package com.growit.api.service;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.Loan;
import com.growit.api.domain.Role;
import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.dto.BorrowerAccountLoanDto;
import com.growit.api.mapper.BorrowerAccountMapper;
import com.growit.api.repo.BorrowerAccountRepo;
import com.growit.api.repo.BorrowerRepo;
import com.growit.api.repo.CreditHistoryRepo;
import com.growit.api.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BorrowerAccountService {

    private final BorrowerAccountMapper mapper;
    private final BorrowerRepo borrowerRepo;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final LoanRepo loanRepo;
    private final CreditHistoryRepo creditHistoryRepo;


    @Autowired
    public BorrowerAccountService(BorrowerAccountMapper mapper, BorrowerRepo borrowerRepo, BorrowerAccountRepo borrowerAccountRepo, LoanRepo loanRepo, CreditHistoryRepo creditHistoryRepo) {
        this.mapper = mapper;
        this.borrowerRepo = borrowerRepo;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.loanRepo = loanRepo;
        this.creditHistoryRepo = creditHistoryRepo;
    }

    @PreAuthorize("hasAnyAuthority('BORROWER_ON_CHECK', 'REGISTERED_BORROWER')") // to be changed: to VERIFIED_BORROWER when verification implemented
    public BorrowerAccountDto getAccountData(Borrower borrower) {
        Loan latestLoan = loanRepo.findByBorrowerAndLatestTrue(borrower);
        return borrower.isVerified() ? new BorrowerAccountDto(
                borrower.getBorrowerAccount().getAvailableBalance(),
                String.format("%.2f", (borrower.getMonthlyRate() / 30))  + "%",
                borrower.getSafetyRank(),
                borrower.getVerificationScore().toString(),
                latestLoan != null ? latestLoan.getStatus().name() : "no current loans",  // npe
                borrower.isVerified()
        ) : new BorrowerAccountDto();
    }

    @PreAuthorize("hasAuthority('BORROWER_ON_CHECK')")
    @Transactional
    public boolean toggleVerification(Borrower borrower) {
        borrower.setVerified(true);
        borrower.setMonthlyRate(20d);
        borrower.setSafetyRank("A");
        borrower.setVerificationScore(83);
        borrower.setCreditHistory(creditHistoryRepo.findById(432L).get());
        borrower.getRoles().add(Role.VERIFIED_BORROWER);
        borrowerRepo.save(borrower);
        return true;
    }

    @PreAuthorize("hasAnyAuthority('BORROWER_ON_CHECK', 'REGISTERED_BORROWER')")
    public List<BorrowerAccountLoanDto> getPreviousLoans(Borrower borrower) {
        ArrayList<BorrowerAccountLoanDto> list = new ArrayList<>();
        ArrayList<Loan> dbList = (ArrayList) loanRepo.findByBorrowerAndLatestFalseOrderByCloseDateDesc(borrower);
        if (dbList.isEmpty()) return Collections.emptyList();
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
