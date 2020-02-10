package com.growit.api.service;

import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.mapper.BorrowerAccountMapper;
import com.growit.api.repo.BorrowerAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerAccountService {

    private final BorrowerAccountMapper mapper;
    private final BorrowerAccountRepo borrowerAccountRepo;

    @Autowired
    public BorrowerAccountService(BorrowerAccountMapper mapper, BorrowerAccountRepo borrowerAccountRepo) {
        this.mapper = mapper;
        this.borrowerAccountRepo = borrowerAccountRepo;
    }


    public BorrowerAccountDto update(BorrowerAccountDto dto) {
        dto.setCreated(borrowerAccountRepo.findById(dto.getId()).get().getCreated());
        return mapper.toDto(borrowerAccountRepo.save(mapper.toEntity(dto)) );
    }

}
