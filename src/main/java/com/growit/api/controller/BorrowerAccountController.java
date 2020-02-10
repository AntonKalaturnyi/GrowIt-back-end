package com.growit.api.controller;

import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.dto.Existing;
import com.growit.api.service.BorrowerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("my/account")
public class BorrowerAccountController {

    private final BorrowerAccountService borrowerAccountService;

    @Autowired
    public BorrowerAccountController(BorrowerAccountService borrowerAccountService) {
        this.borrowerAccountService = borrowerAccountService;
    }


    /** id of InvestorAccount must be privided in dto*/
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerAccountDto update(@Validated(Existing.class) @RequestBody BorrowerAccountDto dto) {
        return borrowerAccountService.update(dto);
    }


}
