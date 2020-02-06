package com.growit.api.controller;

import com.growit.api.dto.New;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.service.BorrowerService;
import com.growit.api.service.InvestorService;
import com.growit.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final InvestorService investorService;
    private final BorrowerService borrowerService;

    @Autowired
    public RegistrationController(UserService userService, InvestorService investorService, BorrowerService borrowerService) {
        this.userService = userService;
        this.investorService = investorService;
        this.borrowerService = borrowerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationDto createUser(@Validated(New.class) @RequestBody UserRegistrationDto dto) {
        return userService.create(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new-investor",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationDto registerInvestor(@Validated(New.class) @RequestBody UserRegistrationDto dto) {
        return investorService.create(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new-borrower",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationDto registerBorrower(@Validated(New.class) @RequestBody UserRegistrationDto dto) {
        return borrowerService.create(dto);
    }

}
