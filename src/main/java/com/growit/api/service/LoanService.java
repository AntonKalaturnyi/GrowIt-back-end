package com.growit.api.service;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.CreditHistory;
import com.growit.api.domain.Loan;
import com.growit.api.dto.DashboardLoanDto;
import com.growit.api.dto.LoanFromCalculatorDto;
import com.growit.api.mapper.LoanMapper;
import com.growit.api.repo.LoanPurposeRepo;
import com.growit.api.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepo loanRepo;
    private final LoanMapper mapper;
    private final LoanPurposeRepo loanPurposeRepo;

    @Autowired
    public LoanService(LoanRepo loanRepo, LoanMapper mapper, LoanPurposeRepo loanPurposeRepo) {
        this.loanRepo = loanRepo;
        this.mapper = mapper;
        this.loanPurposeRepo = loanPurposeRepo;
    }

    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Boolean createLoanFromCalculator(Borrower borrower, LoanFromCalculatorDto dto) {
        Loan loan = new Loan();
        loan.setAmountRequested(dto.getAmount());
        loan.setPeriod(dto.getPeriod());
        loan.setTerm(String.valueOf(loan.getPeriod()));
        loan.setDescription(dto.getDescription());
        loan.setBorrower(borrower);
        loan.setLoanPurpose(loanPurposeRepo.findByPurposeUa(dto.getLoanPurpose()));
        loanRepo.save(loan);
        return true;
    }

    @PreAuthorize("hasAuthority('INVESTOR')")
    public List<DashboardLoanDto> getDashboardLoanList() {
        ArrayList<DashboardLoanDto> list = new ArrayList<>();
        ArrayList<Loan> dbList = (ArrayList) loanRepo.findAll();
        LocalDateTime now = LocalDateTime.now();
        int dayNow = now.getDayOfYear();

        for (Loan loan : dbList) {
            if ((loan.getDateReleasedOnDashboard().plusDays(7).isBefore(LocalDateTime.now()))) continue;
            Borrower borrower = loan.getBorrower();
            CreditHistory history = borrower.getCreditHistory();
            boolean noOpenCr = history.getCurrentOpenCredits() == null || history.getCurrentOpenCredits() == 0;
            list.add(new DashboardLoanDto(
                    loan.getId(),
                    loan.getSafetyRank(),
                    loan.getVerificationScore(),
                    loan.getAmountApproved(),
                    loan.getTerm(),
                    String.valueOf(loan.getProfitability()).replace('.', ','),
                    loan.getLoanPurpose().getPurposeUa(),
                    loan.getDateReleasedOnDashboard().toLocalDate(),
                    loan.getAmountFunded(),
                    (int) Math.round( (loan.getAmountFunded() / loan.getAmountApproved()) * 100),
                    loan.getDescription(),
                    (loan.getDateReleasedOnDashboard().plusDays(7).getDayOfYear()) > dayNow ?
                            ((loan.getDateReleasedOnDashboard().plusDays(7).getDayOfYear()) - dayNow) + " днів" :
                            (LocalDateTime.now().until( loan.getDateReleasedOnDashboard().plusDays(7), ChronoUnit.HOURS) >= 1) ?
                                    now.until( loan.getDateReleasedOnDashboard().plusDays(7), ChronoUnit.HOURS) + " год "
                                            + (now.until(loan.getDateReleasedOnDashboard().plusDays(7), ChronoUnit.MINUTES) - (60 * now.until( loan.getDateReleasedOnDashboard().plusDays(7), ChronoUnit.HOURS))) + " хв" :
                                    now.until( loan.getDateReleasedOnDashboard().plusDays(7), ChronoUnit.MINUTES) + " хв.",
                    borrower.getRegistrationDate().toLocalDate(),
                    borrower.getMaritalStatus(),
                    borrower.getKidsBefore18yo(),
                    borrower.getKidsAfter18yo(),
                    borrower.getAddress().getSettlement() + ", " + borrower.getAddress().getRegion(),
                    borrower.getAge(),
                    borrower.getSocialStatus().getStatusUa(),
                    borrower.getMonthlyIncomeTotal(),
                    borrower.getMonthlyExpenses(),
                    (int) Math.round((borrower.getMonthlyObligations() + loan.getMonthlyPayment()) / borrower.getMonthlyIncomeTotal() * 100), // pti
                    history.getCurrentOpenCredits(),
                    noOpenCr ? 0 : history.getCurrentDebtAmount(),
                    !noOpenCr && history.isHasDelayInCurrentPeriod(),
                    noOpenCr ? 0 : history.getCurrentOverdueDebtAmount(),
                    noOpenCr ? 0 : history.getCurrentDelayInDays(),
                    history.getPayedOffInOtherOrgs(),
                    history.getPayedInGrowit()
            ));
        }
        return list;
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

/*    public LoanDto create(LoanDto dto) {

        Loan loan = mapper.toEntity(dto);

        loan.setAmountFunded(0);
//        loan.setPercentFunded(0);

        loan.setMonthlyPayment(0); // to be implemented
//        loan.setDtiRatio(0); // to be implemented
 *//*       loan.setAmountApproved(0); // to be implemented
        loan.setAmountToReturn(0); // to be implemented
        loan.setProfitability(0); // to be implemented*//*

        LocalDateTime now = LocalDateTime.now();
        loan.setCreated(now);
        loan.setUpdated(now);

*//* === monthlyPayment ===
  int period = loan.getPeriod();
        if (period > 31) {
            if ()
            loan.setMonthlyPayment( loan.getAmountApproved()   );

        }
        === dtiRatio ===
                loan.setDtiRatio((loan.getMonthlyPayment / borrower.getTotalIncome) * 100 );


        *//*
     *//*        === amountApproved ===

         if ( amountRequested > ratesRepo.findByClass(borrowerRepo.findById(dto.getBorrowerId).getBorrowerVerification().getSafetyClass).getMaxAllowedLoanAmount()) {
 throw new RequestedAmountNotAllowedException("Requested is higher than allowed be class!");
    } else {
    loan.amountApproved = loan.amountRequested;
    }
    === amountToReturn === ... by Rates by class

    === profitability ===
    *//*

        return mapper.toDto(loanRepo.save(loan));
    }
}*/
}


