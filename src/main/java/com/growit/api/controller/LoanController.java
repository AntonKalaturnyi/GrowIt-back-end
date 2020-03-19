package com.growit.api.controller;

import com.growit.api.domain.Borrower;
import com.growit.api.dto.LoanDto;
import com.growit.api.dto.LoanFromCalculatorDto;
import com.growit.api.dto.New;
import com.growit.api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, path = "/new-calculator-loan")
    public Boolean orderCalculatorLoan(@AuthenticationPrincipal Borrower borrower, @Validated(New.class) @RequestBody LoanFromCalculatorDto dto) {
        return loanService.createLoanFromCalculator(borrower, dto);
    }

    // returnLoan() {if amount == limit, borrowerverification.setFirstLimitReturned or second}




}
