package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.AuthDto;
import com.growit.api.dto.InvestmentDto;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.exceptions.AccountOverdraftException;
import com.growit.api.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Service
public class InvestorService implements UserDetailsService {

    private final InvestorRepo investorRepo;
    private final PasswordEncoder passwordEncoder;
    private final InvestorAccountRepo investorAccountRepo;
    private final BorrowerRepo borrowerRepo;
    private final InvestmentRepo investmentRepo;
    private final AuthService authService;
    private final LoanRepo loanRepo;

    @Autowired
    public InvestorService(InvestorRepo investorRepo, PasswordEncoder passwordEncoder, InvestorAccountRepo investorAccountRepo,
                           BorrowerRepo borrowerRepo, InvestmentRepo investmentRepo, @Lazy AuthService authService, LoanRepo loanRepo) {
        this.investorRepo = investorRepo;
        this.passwordEncoder = passwordEncoder;
        this.investorAccountRepo = investorAccountRepo;
        this.borrowerRepo = borrowerRepo;
        this.investmentRepo = investmentRepo;
        this.authService = authService;
        this.loanRepo = loanRepo;
    }

    @Transactional
    public UserRegistrationDto create(UserRegistrationDto dto) {
        Investor investor =  investorRepo.findByEmail(dto.getEmail());

        if (investor.getBirthday() == null) {
          UserService.setUserFields(investor, dto);
        }

            investor.setAge(Period.between(investor.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
            investor.setActive(true);
            InvestorAccount account = investorAccountRepo.save(new InvestorAccount());
            account.setInvestor(investor);
            account = investorAccountRepo.save(account);
            investor.setAccount(account);

        return new UserRegistrationDto(investorRepo.save(investor) );
    }

    @Transactional
    public ResponseEntity createWithCredentialsAndLogin(AuthDto creds) {
        Investor investor;
        Borrower borrower = borrowerRepo.findByEmail(creds.getUsername());

        if (borrower != null) {
            investor = new Investor(borrower);
        } else {
            investor = new Investor();
            investor.setEmail(creds.getUsername());
        }
        investor.setPassword(passwordEncoder.encode(creds.getPassword()));
        UserService.setRegisteredUserRole(investor);
        investor.setActive(true); // add email activation for this
        investorRepo.save(investor);
        creds.setUsername(creds.getUsername());
        return authService.signIn(creds);
    }

    @Transactional
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
        throw new AccountOverdraftException("Not enough funds on account to meke this investment!");
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return investorRepo.findByEmail(email);
    }
}

