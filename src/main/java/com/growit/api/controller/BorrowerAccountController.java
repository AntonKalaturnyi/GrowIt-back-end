package com.growit.api.controller;

import com.growit.api.domain.Borrower;
import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.dto.BorrowerAccountLoanDto;
import com.growit.api.dto.DashboardLoanDto;
import com.growit.api.dto.EmploymentDto;
import com.growit.api.service.BorrowerAccountService;
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
@RequestMapping("borrower-account")
public class BorrowerAccountController {

    private final BorrowerAccountService borrowerAccountService;

    @Autowired
    public BorrowerAccountController(BorrowerAccountService borrowerAccountService) {
        this.borrowerAccountService = borrowerAccountService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerAccountDto getGeneralData(@AuthenticationPrincipal Borrower borrower) {
        return borrowerAccountService.getAccountData(borrower);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/verification", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean toggleVerification(@AuthenticationPrincipal Borrower borrower) {
        return borrowerAccountService.toggleVerification(borrower);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get-loans", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BorrowerAccountLoanDto> getPreviousLoansOfBorrower(@AuthenticationPrincipal Borrower borrower) {
        return borrowerAccountService.getPreviousLoans(borrower);
    }

/*    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerAccountDto update(@Validated(Existing.class) @RequestBody BorrowerAccountDto dto) {
        return borrowerAccountService.update(dto);
    }*/




}
