package com.growit.api.service;

import com.growit.api.dto.CreditHistoryDto;
import com.growit.api.mapper.CreditHistoryMapper;
import com.growit.api.repo.CreditHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditHistoryService {

    private final CreditHistoryMapper mapper;
    private final CreditHistoryRepo creditHistoryRepo;

    @Autowired
    public CreditHistoryService(CreditHistoryMapper mapper, CreditHistoryRepo creditHistoryRepo) {
        this.mapper = mapper;
        this.creditHistoryRepo = creditHistoryRepo;
    }

    public CreditHistoryDto create(CreditHistoryDto dto) {
        return mapper.toDto(creditHistoryRepo.save(mapper.toEntity(dto)));
    }

}
