package com.growit.api.controller;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.Investor;
import com.growit.api.dto.CabinetInvestmentDto;
import com.growit.api.dto.CalculatorLoanOnFundingDto;
import com.growit.api.dto.InvestorAccountDto;
import com.growit.api.dto.TransactionDto;
import com.growit.api.service.InvestorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/investor-account")
public class InvestorAccountController {

    private final InvestorAccountService investorAccountService;

    @Autowired
    public InvestorAccountController(InvestorAccountService investorAccountService) {
        this.investorAccountService = investorAccountService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public InvestorAccountDto getGeneralData(@AuthenticationPrincipal Investor investor) {
        return investorAccountService.getAccountData(investor);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/investments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CabinetInvestmentDto> getInvestments(@AuthenticationPrincipal Investor investor) {
        return investorAccountService.getInvestments(investor);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDto> getTransactions(@AuthenticationPrincipal Investor investor) {
        return investorAccountService.getTransactions(investor);
    }
}
