package com.growit.api.service;

import com.growit.api.domain.Investor;
import com.growit.api.domain.InvestorAccount;
import com.growit.api.domain.Role;
import com.growit.api.dto.InvestorAccountDto;
import com.growit.api.repo.InvestorAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvestorAccountService {

    private final InvestorAccountRepo investorAccountRepo;

    @Autowired
    public InvestorAccountService(InvestorAccountRepo investorAccountRepo) {
        this.investorAccountRepo = investorAccountRepo;
    }

    @Transactional
    @PreAuthorize("hasAnyAuthority('REGISTERED_INVESTOR')")
    public InvestorAccountDto getAccountData(Investor investor) {
        InvestorAccount account = investor.getAccount();
        return investor.getRoles().contains(Role.INVESTOR) ? new InvestorAccountDto(account.getAvailableBalance(), account.getInvestedFunds()) :
                new InvestorAccountDto();
    }
}
