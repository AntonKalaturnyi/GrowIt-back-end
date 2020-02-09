package com.growit.api.controller;

import com.growit.api.dto.LoanDto;
import com.growit.api.dto.New;
import com.growit.api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
            consumes = MediaType.APPLICATION_JSON_VALUE, path = "/create")
    public LoanDto createLoan(@Validated(New.class) @RequestBody LoanDto loanDto) {
        return loanService.create(loanDto);
    }




}
