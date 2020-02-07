package com.growit.api.controller;

import com.growit.api.dto.InvestmentDto;
import com.growit.api.dto.New;
import com.growit.api.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invest")
public class InvestController {

    private final InvestorService investorService;

    @Autowired
    public InvestController(InvestorService investorService) {
        this.investorService = investorService;
    }

    //  @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerUpdateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public InvestmentDto acceptInvestment(@Validated(New.class) @RequestBody InvestmentDto dto) {
        return investorService.makeInvestment(dto);
    }
}
