package com.growit.api.service;

import com.growit.api.domain.Investment;
import com.growit.api.domain.Investor;
import com.growit.api.domain.InvestorAccount;
import com.growit.api.domain.Role;
import com.growit.api.dto.InvestmentDto;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.exceptions.AccountOverdraftException;
import com.growit.api.repo.InvestmentRepo;
import com.growit.api.repo.InvestorAccountRepo;
import com.growit.api.repo.InvestorRepo;
import com.growit.api.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Service
public class InvestorService {

    private final InvestorRepo investorRepo;
    private final PasswordEncoder passwordEncoder;
    private final InvestorAccountRepo investorAccountRepo;
    private final InvestmentRepo investmentRepo;
    private final LoanRepo loanRepo;

    @Autowired
    public InvestorService(InvestorRepo investorRepo, PasswordEncoder passwordEncoder, InvestorAccountRepo investorAccountRepo, InvestmentRepo investmentRepo, LoanRepo loanRepo) {
        this.investorRepo = investorRepo;
        this.passwordEncoder = passwordEncoder;
        this.investorAccountRepo = investorAccountRepo;
        this.investmentRepo = investmentRepo;
        this.loanRepo = loanRepo;
    }

    public UserRegistrationDto create(UserRegistrationDto dto) {
        Investor investor =  investorRepo.save(new Investor(dto));
        investor.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.REGISTERED_USER);
        investor.setRoles(roles);
        investor.setAge(Period.between(investor.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        investor.setActive(true);
        investor.setLastVisit(LocalDateTime.now());
        InvestorAccount account = investorAccountRepo.save(new InvestorAccount());
        account.setInvestor(investor);
        account = investorAccountRepo.save(account);
        investor.setAccount(account);
        return new UserRegistrationDto(investorRepo.save(investor) );
    }

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

}

