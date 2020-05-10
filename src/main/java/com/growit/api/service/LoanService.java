package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.CalculatorLoanOnFundingDto;
import com.growit.api.dto.DashboardLoanDto;
import com.growit.api.dto.LoanFromCalculatorDto;
import com.growit.api.repo.LoanPurposeRepo;
import com.growit.api.repo.LoanRepo;
import com.growit.api.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private static NumberFormat formatter = new DecimalFormat("#0.00");

    private final LoanRepo loanRepo;
    private final LoanPurposeRepo loanPurposeRepo;

    @Autowired
    public LoanService(LoanRepo loanRepo, LoanPurposeRepo loanPurposeRepo) {
        this.loanRepo = loanRepo;
        this.loanPurposeRepo = loanPurposeRepo;
    }

    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    @Transactional
    public Boolean createLoanFromCalculator(Borrower borrower, LoanFromCalculatorDto dto) {
        Loan prev = loanRepo.findByBorrowerAndLatestTrue(borrower);
        Loan loan = prev == null ? new Loan(): prev;
        loan.setAmountRequested(dto.getAmount());
        loan.setPeriod(dto.getPeriod());
        loan.setTerm(loan.getPeriod() + "d");
        loan.setDescription(dto.getDescription());
        loan.setBorrower(borrower);
        loan.setLatest(true);
        if (borrower.getRoles().contains(Role.VERIFIED_BORROWER)) {

            loan.setStatus(LoanStatus.ON_FUNDING);
            loan.setDateReleasedOnDashboard(LocalDateTime.now());
            loan.setCloseDate(loan.getDateReleasedOnDashboard().plusDays(7));
            loan.setSafetyRank(borrower.getSafetyRank());
            loan.setVerificationScore(borrower.getVerificationScore());
            loan.setAmountApproved(loan.getAmountRequested());
            loan.setMonthlyRate(borrower.getMonthlyRate());
            loan.setMonthlyPayment(loan.getAmountApproved());
            loan.setAmountToReturn(calculateAmountToReturn(loan.getMonthlyRate(), loan.getPeriod(), loan.getAmountApproved()));
        } else {
            loan.setStatus(LoanStatus.DRAFTED_ON_CALCULATOR);
        }
        loan.setLoanPurpose(loanPurposeRepo.findByPurposeUa(dto.getLoanPurpose()));
        loanRepo.save(loan);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('VERIFIED_BORROWER')")
    public boolean deleteCurrentCabinetLoan(Borrower borrower) {
        Loan loan = loanRepo.findByBorrowerAndLatestTrueAndStatus(borrower, LoanStatus.ON_FUNDING);
        loanRepo.deleteWithId(loan.getId());
        return true;
    }


    @PreAuthorize("hasAnyAuthority('BORROWER_ON_CHECK', 'VERIFIEED_BORROWER', 'REGISTERED_BORROWER')")
    @Transactional
    public List<CalculatorLoanOnFundingDto> getCurrentCabinetLoan(Borrower borrower) {
        Loan latestLoan = loanRepo.findByBorrowerAndLatestTrueAndStatus(borrower, LoanStatus.ON_FUNDING);
        if (latestLoan != null) {
            CreditHistory history = borrower.getCreditHistory();
            boolean noOpenCr = history.getCurrentOpenCredits() == null || history.getCurrentOpenCredits() == 0;

            ArrayList<CalculatorLoanOnFundingDto> list = new ArrayList<>();
            list.add( new CalculatorLoanOnFundingDto(
                    latestLoan.getId(),
                    latestLoan.getAmountApproved(),
                    latestLoan.getTerm(),
                    latestLoan.getAmountToReturn().toString(), latestLoan.getLoanPurpose().getPurposeEng(),
                    latestLoan.getDateReleasedOnDashboard().toLocalDate(),
                    latestLoan.getAmountFunded(),
                    (int) Math.round( (latestLoan.getAmountFunded() / latestLoan.getAmountApproved()) * 100),
                    latestLoan.getDescription(),
                    latestLoan.getCloseDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yy"))
                            + " (" + ((latestLoan.getDateReleasedOnDashboard().plusDays(7).getDayOfYear()) - LocalDateTime.now().getDayOfYear()) + "дн)",
                    borrower.getRegistrationDate().toLocalDate(),
                    borrower.getMaritalStatus(),
                    borrower.getKidsBefore18yo(),
                    borrower.getKidsAfter18yo(),
                    borrower.getAddress().getSettlement() + ", " + borrower.getAddress().getRegion(),
                    borrower.getAge(),
                    borrower.getSocialStatus(),
                    borrower.getMonthlyIncomeTotal(),
                    borrower.getMonthlyExpenses(),
                    (int) Math.round((borrower.getMonthlyObligations() + latestLoan.getMonthlyPayment()) / borrower.getMonthlyIncomeTotal() * 100), // pti
                    history.getCurrentOpenCredits(),
                    noOpenCr ? 0 : history.getCurrentDebtAmount(),
                    !noOpenCr && history.isHasDelayInCurrentPeriod(),
                    noOpenCr ? 0 : history.getCurrentOverdueDebtAmount(),
                    noOpenCr ? 0 : history.getCurrentDelayInDays(),
                    history.getPayedOffInOtherOrgs(),
                    history.getPayedInGrowit()
            ));
            return list;
        } return new ArrayList<>();
    }


    @PreAuthorize("hasAnyAuthority('BORROWER_ON_CHECK', 'VERIFIEED_BORROWER', 'REGISTERED_BORROWER')") // to be changed: to VERIFIED_BORROWER when verification implemented
    @Transactional
    public LoanFromCalculatorDto getCalculatorLoan(Borrower borrower) {
        Loan latestLoan = loanRepo.findByBorrowerAndLatestTrueAndStatus(borrower, LoanStatus.DRAFTED_ON_CALCULATOR);
        return latestLoan != null ?
                new LoanFromCalculatorDto(latestLoan.getAmountRequested(), latestLoan.getPeriod(), latestLoan.getLoanPurpose().getPurposeUa(), latestLoan.getDescription()) :
                new LoanFromCalculatorDto();
    }

    @PreAuthorize("hasAuthority('REGISTERED_INVESTOR')")
    public List<DashboardLoanDto> getDashboardLoanList() {
        ArrayList<DashboardLoanDto> list = new ArrayList<>();
        ArrayList<Loan> dbList = (ArrayList) loanRepo.findAll();
        LocalDateTime now = LocalDateTime.now();
        int dayNow = now.getDayOfYear();

        for (Loan loan : dbList) {
            if (loan.getDateReleasedOnDashboard() == null || (loan.getDateReleasedOnDashboard().plusDays(7).isBefore(LocalDateTime.now()))) continue;
            Borrower borrower = loan.getBorrower();
            CreditHistory history = borrower.getCreditHistory();
            boolean noOpenCr = history.getCurrentOpenCredits() == null || history.getCurrentOpenCredits() == 0;
            list.add(new DashboardLoanDto(
                    loan.getId(),
                    loan.getSafetyRank(),
                    loan.getVerificationScore(),
                    loan.getAmountApproved(),
                    loan.getTerm(),
                    calculateProfitability(loan.getMonthlyRate(), Integer.parseInt(loan.getTerm().substring(0, loan.getTerm().length() - 1)), loan.getAmountApproved()).replace('.', ','), // String.valueOf((loan.getMonthlyRate() / 30) * 365).replace('.', ','),
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
                    borrower.getSocialStatus(),
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

    private String calculateProfitability(double monthlyRate, int days, int amount) {
        return formatter.format((((((((((((monthlyRate / 30)*days)*0.01)+1)*amount)*0.985)*0.98) / amount) - 1) * 100) / days) * 365);
    }

    private double calculateAmountToReturn(double monthlyRate, int days, int amount) {
        return ConstantUtil.round(((((monthlyRate / 30)*days)*0.01)+1)*amount , 2);
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


