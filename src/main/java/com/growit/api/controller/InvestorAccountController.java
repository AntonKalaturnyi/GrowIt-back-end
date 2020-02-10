package com.growit.api.controller;

import com.growit.api.dto.Existing;
import com.growit.api.dto.InvestorAccountDto;
import com.growit.api.service.InvestorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investor-account")
public class InvestorAccountController {

    private final InvestorAccountService investorAccountService;

    @Autowired
    public InvestorAccountController(InvestorAccountService investorAccountService) {
        this.investorAccountService = investorAccountService;
    }

    /** id of InvestorAccount must be privided in dto*/
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public InvestorAccountDto update(@Validated(Existing.class) @RequestBody InvestorAccountDto dto) {
        return investorAccountService.update(dto);
    }
}
