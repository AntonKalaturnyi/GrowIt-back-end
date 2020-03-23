package com.growit.api.controller;

import com.growit.api.domain.Investor;
import com.growit.api.dto.InvestmentDto;
import com.growit.api.dto.InvestorPassportAndItnDto;
import com.growit.api.dto.InvestorRegDto;
import com.growit.api.dto.New;
import com.growit.api.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invest")
public class InvestorController {

    private final InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/fill-investor",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer fillInvestorAndSendSms(@AuthenticationPrincipal Investor investor, @Validated(New.class) @RequestBody InvestorRegDto dto) {
        return investorService.fillPersonalInfoAndSendSmsCode(investor, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/investor-save-passport-itn",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean saveInvestorPassportAndItn(@Validated(New.class) @RequestBody InvestorPassportAndItnDto dto) {
        return investorService.savePassportAndItn(dto);
    }

    //  @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerUpdateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public InvestmentDto acceptInvestment(@Validated(New.class) @RequestBody InvestmentDto dto) {
        return investorService.makeInvestment(dto);
    }
}
