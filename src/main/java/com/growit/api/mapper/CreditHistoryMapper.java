package com.growit.api.mapper;

import com.growit.api.domain.CreditHistory;
import com.growit.api.dto.CreditHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryMapper extends AbstractMapper<CreditHistory, CreditHistoryDto> {

    @Autowired
    public CreditHistoryMapper() {
        super(CreditHistory.class, CreditHistoryDto.class);
    }
}
