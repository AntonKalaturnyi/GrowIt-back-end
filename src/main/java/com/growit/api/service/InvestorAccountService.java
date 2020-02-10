package com.growit.api.service;

import com.growit.api.dto.InvestorAccountDto;
import com.growit.api.mapper.InvestorAccountMapper;
import com.growit.api.repo.InvestorAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorAccountService {

    private final InvestorAccountRepo investorAccountRepo;
    private final InvestorAccountMapper mapper;

    @Autowired
    public InvestorAccountService(InvestorAccountRepo investorAccountRepo, InvestorAccountMapper mapper) {
        this.investorAccountRepo = investorAccountRepo;
        this.mapper = mapper;
    }

    public InvestorAccountDto update(InvestorAccountDto dto) {
        dto.setCreated(investorAccountRepo.findById(dto.getId()).get().getCreated());
        return mapper.toDto(investorAccountRepo.save(mapper.toEntity(dto)) );
    }


}
