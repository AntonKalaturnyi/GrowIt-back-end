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
import java.time.Period;
import java.util.Random;

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
    public Integer fillPersonalInfoAndSendSmsCode(InvestorRegDto dto) {
        Investor investor = investorRepo.findByEmail(dto.getEmail());
        investor.setName(dto.getName());
        investor.setMiddleName(dto.getMiddleName());
        investor.setLastName(dto.getLastName());
        investor.setGender(dto.getGender());
        investor.setBirthday(dto.getBirthday());
        investor.setPhone(dto.getPhone());
        investor.setAge(Period.between(investor.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        investor.setLastVisit(LocalDateTime.now());
        investor.setActive(true);
        InvestorAccount account = investorAccountRepo.save(new InvestorAccount());
        account.setInvestor(investor);
        account = investorAccountRepo.save(account);
        investor.setAccount(account);
        investorRepo.save(investor);
        return ConstantUtil.getRandom6DigitNumber();
    }


    @Transactional
    public Boolean savePassportAndItn(InvestorPassportAndItnDto dto) {
        Investor investor = investorRepo.findByEmail(dto.getEmail());
        investor.setPassport(passportService.createInvestorPass(dto));
        investor.setItn(dto.getItnNumber());
        investorRepo.save(investor);
        return true;
    }


    @Transactional
    public ResponseEntity createWithCredentials(AuthDto creds) {
        Borrower borrower = borrowerRepo.findByEmail(creds.getUsername());
        if (borrower != null) {
            UserService.addInvestorRole(borrower);
            return authService.signIn(creds);
        }
        Investor investor = new Investor();
        investor.setEmail(creds.getUsername());
        investor.setPassword(passwordEncoder.encode(creds.getPassword()));
        UserService.setRegisteredUserRole(investor);
        investor.setActive(true); // add email activation for this
        investor.setLastVisit(LocalDateTime.now());
        investorRepo.save(investor);
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

