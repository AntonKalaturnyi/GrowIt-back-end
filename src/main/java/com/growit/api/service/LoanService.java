package com.growit.api.service;

import com.growit.api.domain.Loan;
import com.growit.api.dto.LoanDto;
import com.growit.api.mapper.LoanMapper;
import com.growit.api.repo.BorrowerRepo;
import com.growit.api.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanService {

    private final LoanRepo loanRepo;
    private final LoanMapper mapper;
    private final BorrowerRepo borrowerRepo;

    @Autowired
    public LoanService(LoanRepo loanRepo, LoanMapper mapper, BorrowerRepo borrowerRepo) {
        this.loanRepo = loanRepo;
        this.mapper = mapper;
        this.borrowerRepo = borrowerRepo;
    }

    /**
    {
        "amountApproved": 6900,
            "amountFunded": 0,
            "amountRequested": 7800,
            "amountToReturn": 7500,
            "borrowerId": 10,
            "created": null,
            "dateReturnDue": "2020-06-12 15:00:00.000",
            "description": "Хочу купити тостер",
            "dtiRatio": 0,
            "id": null,
            "loanPurpose": "Debt consolidation",
            "loanStatus": "Created",
            "monthlyPayment": 0,
            "percentFunded": 0,
            "period": 35,
            "profitability": 9.8,
            "updated": null
    }
     */

    public LoanDto create(LoanDto dto) {

        Loan loan = mapper.toEntity(dto);

        loan.setAmountFunded(0);
        loan.setPercentFunded(0);

        loan.setMonthlyPayment(0); // to be implemented
        loan.setDtiRatio(0); // to be implemented
 /*       loan.setAmountApproved(0); // to be implemented
        loan.setAmountToReturn(0); // to be implemented
        loan.setProfitability(0); // to be implemented*/

        LocalDateTime now = LocalDateTime.now();
        loan.setCreated(now);
        loan.setUpdated(now);

/* === monthlyPayment ===
  int period = loan.getPeriod();
        if (period > 31) {
            if ()
            loan.setMonthlyPayment( loan.getAmountApproved()   );

        }
        === dtiRatio ===
                loan.setDtiRatio((loan.getMonthlyPayment / borrower.getTotalIncome) * 100 );


        */
/*        === amountApproved ===

         if ( amountRequested > ratesRepo.findByClass(borrowerRepo.findById(dto.getBorrowerId).getBorrowerVerification().getSafetyClass).getMaxAllowedLoanAmount()) {
 throw new RequestedAmountNotAllowedException("Requested is higher than allowed be class!");
    } else {
    loan.amountApproved = loan.amountRequested;
    }
    === amountToReturn === ... by Rates by class

    === profitability ===
    */

        return mapper.toDto(loanRepo.save(loan));
    }
}
