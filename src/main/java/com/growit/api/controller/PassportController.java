package com.growit.api.controller;

import com.growit.api.domain.Card;
import com.growit.api.domain.Passport;
import com.growit.api.dto.BorrowerPassportDto;
import com.growit.api.dto.Existing;
import com.growit.api.dto.InvestorPassportDto;
import com.growit.api.dto.New;
import com.growit.api.service.PassportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

/*    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new-borrower-passport", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Passport setBorrowerPassport(@Validated(New.class) @RequestBody BorrowerPassportDto dto) {
        return passportService.createBorrowerPass(dto);
    }*/

/*    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update-borrower-passport", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Passport updateBorrowerPassport(@Validated(Existing.class) @RequestBody BorrowerPassportDto dto) {
        return passportService.updateBorrowerPass(dto);
    }*/

/*    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new-investor-passport", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public InvestorPassportDto setInvestorPassport(@Validated(New.class) @RequestBody InvestorPassportDto dto) {
        return passportService.createInvestorPass(dto);
    }*/
}
