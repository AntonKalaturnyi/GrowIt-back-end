package com.growit.api.controller;

import com.growit.api.dto.BorrowerDto;
import com.growit.api.dto.CreditCardDto;
import com.growit.api.dto.New;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private final BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/add-basic-info",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationDto fillBasicData(@Validated(New.class) @RequestBody UserRegistrationDto dto) {
        return borrowerService.fillBasicInfo(dto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/fill-borrower",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerDto fillBorrower(@Validated(New.class) @RequestBody BorrowerDto dto) {
        return borrowerService.fill(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new-card",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CreditCardDto setCreditCard(CreditCardDto dto) {

        return new CreditCardDto();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/contact-persons",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    CreditCardDto setContactPersons(CreditCardDto dto) {

        return new CreditCardDto();
    }

    /*
+Itn
 +Address
 +Passport*/
}
