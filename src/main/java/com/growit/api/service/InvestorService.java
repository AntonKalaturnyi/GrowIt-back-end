package com.growit.api.service;

import com.growit.api.domain.Investor;
import com.growit.api.domain.InvestorAccount;
import com.growit.api.dto.InvestmentDto;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.repo.InvestorAccountRepo;
import com.growit.api.repo.InvestorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;

@Service
public class InvestorService {

    private final InvestorRepo investorRepo;
    private final PasswordEncoder passwordEncoder;
    private final InvestorAccountRepo investorAccountRepo;

    @Autowired
    public InvestorService(InvestorRepo investorRepo, PasswordEncoder passwordEncoder, InvestorAccountRepo investorAccountRepo) {
        this.investorRepo = investorRepo;
        this.passwordEncoder = passwordEncoder;
        this.investorAccountRepo = investorAccountRepo;
    }

    public UserRegistrationDto create(UserRegistrationDto dto) {
        Investor investor =  investorRepo.save(new Investor(dto));
        investor.setPassword(passwordEncoder.encode(dto.getPassword()));
        investor.setAge(Period.between(investor.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        investor.setActive(true);
        InvestorAccount account = investorAccountRepo.save(new InvestorAccount());
        account.setInvestor(investor);
        account = investorAccountRepo.save(account);
        investor.setAccount(account);
        return new UserRegistrationDto(investorRepo.save(investor) );
    }

    public InvestmentDto makeInvestment(InvestmentDto dto) {

        return null;
    }

}

