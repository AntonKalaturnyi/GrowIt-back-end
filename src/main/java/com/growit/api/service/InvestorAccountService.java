package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.CabinetInvestmentDto;
import com.growit.api.dto.InvestorAccountDto;
import com.growit.api.dto.TransactionDto;
import com.growit.api.repo.InvestmentRepo;
import com.growit.api.repo.InvestorAccountRepo;
import com.growit.api.repo.LoanRepo;
import com.growit.api.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvestorAccountService {

    private final InvestorAccountRepo investorAccountRepo;
    private final LoanRepo loanRepo;
    private final InvestmentRepo investmentRepo;
    private final TransactionRepo transactionRepo;
    private static NumberFormat formatter = new DecimalFormat("#0.00");


    @Autowired
    public InvestorAccountService(InvestorAccountRepo investorAccountRepo, LoanRepo loanRepo, InvestmentRepo investmentRepo, TransactionRepo transactionRepo) {
        this.investorAccountRepo = investorAccountRepo;
        this.loanRepo = loanRepo;
        this.investmentRepo = investmentRepo;
        this.transactionRepo = transactionRepo;
    }

    @Transactional
    @PreAuthorize("hasAnyAuthority('REGISTERED_INVESTOR')")
    public InvestorAccountDto getAccountData(Investor investor) {
        InvestorAccount account = investor.getAccount();
        return investor.getRoles().contains(Role.INVESTOR) ? new InvestorAccountDto(account.getAvailableBalance(), account.getInvestedFunds()) :
                new InvestorAccountDto();
    }

    @Transactional
    @PreAuthorize("hasAuthority('INVESTOR')")
    public List<CabinetInvestmentDto> getInvestments(Investor investor) {
        ArrayList<CabinetInvestmentDto> list = new ArrayList<>();
        ArrayList<Investment> investments = (ArrayList) investmentRepo.findAllByInvestor(investor);
        LocalDateTime now = LocalDateTime.now();
        int dayNow = now.getDayOfYear();
        Loan loan;
        for (Investment investment : investments) {
            loan = investment.getLoan();
            if (loan.getDateReleasedOnDashboard() == null || (loan.getDateReleasedOnDashboard().plusDays(7).isBefore(LocalDateTime.now()))) continue;
            Borrower borrower = loan.getBorrower();
            CreditHistory history = borrower.getCreditHistory();
            boolean noOpenCr = history.getCurrentOpenCredits() == null || history.getCurrentOpenCredits() == 0;
            list.add(new CabinetInvestmentDto(
                            investment.getId(),
                            loan.getAmountApproved(),
                            investment.getAmountInvested(),
                            loan.getTerm(),
                            LoanService.calculateProfitability(loan.getMonthlyRate(), Integer.parseInt(loan.getTerm().substring(0, loan.getTerm().length() - 1)), loan.getAmountApproved()).replace('.', ','), // String.valueOf((loan.getMonthlyRate() / 30) * 365).replace('.', ','),
                            calculateReturn(loan.getMonthlyRate(), Integer.parseInt(loan.getTerm().substring(0, loan.getTerm().length() - 1)), (int) investment.getAmountInvested()),
                            loan.getLoanPurpose().getPurposeEng(),
                            loan.getAmountFunded(),
                            (int) Math.round( (loan.getAmountFunded() / loan.getAmountApproved()) * 100),
                            loan.getDescription(),
                            loan.getCloseDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yy"))
                                    + " (" + ((loan.getDateReleasedOnDashboard().plusDays(7).getDayOfYear()) - dayNow) + "дн)",
                            investment.getCreated().format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss")),
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
                    )
            );
        }
        return list;
    }

    @Transactional
    @PreAuthorize("hasAuthority('INVESTOR')")
    public List<TransactionDto> getTransactions(Investor investor) {
        ArrayList<TransactionDto> list = new ArrayList<>();
        ArrayList<Transaction> transactions = (ArrayList) transactionRepo.findAllByAccountOrderByCreatedAsc(investor.getAccount());
        for (Transaction transaction : transactions) {

            list.add(new TransactionDto(
                    transaction.getCreated(),
                    transaction.getAmount(),
                    transaction.getCommission(),
                    transaction.getStatus().name(),
                    transaction.getType(),
                    transaction.getDescription(),
                    transaction.getPreviousBalance()
                    )
            );
        }
        System.out.println("Trans list|" + list + "|");
        return list;
    }

    public static String calculateReturn(double monthlyRate, int days, int amount) {
        return formatter.format((((((monthlyRate / 30)*days)*0.01)+1)*amount)*0.985);
    }


}
