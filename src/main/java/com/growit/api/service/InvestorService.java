package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.AuthDto;
import com.growit.api.dto.InvestmentDto;
import com.growit.api.dto.InvestorPassportAndItnDto;
import com.growit.api.dto.InvestorRegDto;
import com.growit.api.exceptions.AccountOverdraftException;
import com.growit.api.repo.*;
import com.growit.api.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvestorService implements UserDetailsService {

    private final InvestorRepo investorRepo;
    private final PasswordEncoder passwordEncoder;
    private final PassportService passportService;
    private final InvestorAccountRepo investorAccountRepo;
    private final BorrowerRepo borrowerRepo;
    private final InvestmentRepo investmentRepo;
    private final AuthService authService;
    private final LoanRepo loanRepo;

    @Autowired
    public InvestorService(InvestorRepo investorRepo, PasswordEncoder passwordEncoder, PassportService passportService, InvestorAccountRepo investorAccountRepo,
                           BorrowerRepo borrowerRepo, InvestmentRepo investmentRepo, @Lazy AuthService authService, LoanRepo loanRepo) {
        this.investorRepo = investorRepo;
        this.passwordEncoder = passwordEncoder;
        this.passportService = passportService;
        this.investorAccountRepo = investorAccountRepo;
        this.borrowerRepo = borrowerRepo;
        this.investmentRepo = investmentRepo;
        this.authService = authService;
        this.loanRepo = loanRepo;
    }

    @Transactional
    public Integer fillPersonalInfoAndSendSmsCode(Investor investor, InvestorRegDto dto) {
        UserService.setUserFields(investor, dto);
        InvestorAccount account = investorAccountRepo.save(new InvestorAccount());
        account.setInvestor(investor);
        account = investorAccountRepo.save(account);
        investor.setAccount(account);
        investor.setInvestments(new HashSet<>());
        investorRepo.save(investor);
        return ConstantUtil.getRandom6DigitNumber();
    }


    @Transactional
    public Boolean savePassportAndItn(Investor investor, InvestorPassportAndItnDto dto) {
        investor.setPassport(passportService.createInvestorPass(dto));
        investor.setItn(dto.getItnNumber());
        investor.getRoles().add(Role.INVESTOR);
        investorRepo.save(investor);
        return true;
    }


    @Transactional
    public ResponseEntity createWithCredentials(AuthDto creds) {
        Borrower borrower = borrowerRepo.findByEmail(creds.getUsername());
        Investor investor;
        if (borrower != null) {
            // copyFromBorrowerProfile document
//            UserService.addInvestorRole(borrower);
            return authService.signIn(creds);
        } else {
            investor = new Investor();
            Set<Role> roles = new HashSet<>();
            roles.add(Role.REGISTERED_INVESTOR);
            investor.setRoles(roles);
        }
        investor.setEmail(creds.getUsername());
        investor.setPassword(passwordEncoder.encode(creds.getPassword()));
        investor.setActive(true); // add email activation for this
        investor.setLastVisit(LocalDateTime.now());
        investorRepo.save(investor);
        return authService.signIn(creds);
    }

/*    @Transactional
    public InvestmentDto makeInvestment(InvestmentDto dto) {

        Investor investor = investorRepo.findById(dto.getInvestorId()).get();
        InvestorAccount account = investor.getAccount();
        double balance = account.getAvailableBalance();

        if (balance >= dto.getAmountInvested()) {

            account.setAvailableBalance(balance - dto.getAmountInvested());
            investorAccountRepo.save(account);

            Investment investment = new Investment();
            investment.setAmountInvested(dto.getAmountInvested());
            investment.setInvestor(investor);
            investment.setLoan(loanRepo.findById(dto.getLoanId()).get());
            return new InvestmentDto(investmentRepo.save(investment));
        }
        throw new AccountOverdraftException("Not enough funds on account to make this investment!");
    }*/

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return investorRepo.findByEmail(email);
    }

    @Transactional
    public boolean makeInvestments(Investor investor, List<InvestmentDto> dtos) {
        investor = investorRepo.save(investor);
        Investment investment;
        Loan loan;
        Set<Investment> investments = investor.getInvestments();
        InvestorAccount account = investor.getAccount();
        for (InvestmentDto dto: dtos) {
            if (account.getAvailableBalance() >= dto.getAmount()) {
                loan = loanRepo.findById(dto.getLoanId()).get();
                loan.setAmountFunded(loan.getAmountFunded() + dto.getAmount());
                account.setAvailableBalance(account.getAvailableBalance() - dto.getAmount());
                account.setInvestedFunds(account.getInvestedFunds() + dto.getAmount());
                investment = new Investment();
                investment.setAmountInvested(dto.getAmount());
                investment.setLoan(loan);
                investment.setInvestor(investor);
                investments.add(investment);
            } else {
                throw new AccountOverdraftException("Not enough funds on account to make this investment!");
            }
        }
        return true;
    }
}

