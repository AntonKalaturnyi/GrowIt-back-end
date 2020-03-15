package com.growit.api.controller;

import com.growit.api.dto.*;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/fill-borrower",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer fillInvestorAndSendSms(@Validated(New.class) @RequestBody BorrowerRegDto dto) {
        return borrowerService.fillPersonalInfoAndSendSmsCode(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationDto update(@Validated(Existing.class) @RequestBody BorrowerUpdateDto dto) {
        return borrowerService.updateBorrower(dto);
    }


    /***  Implement  ***/
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/change-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationDto changeEmail(@Validated(Existing.class) @RequestBody UserRegistrationDto dto) {
      //  return borrowerService.updateEmail(dto);
        return null;
    }


/*    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/fill-borrower",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerDto fillBorrower(@Validated(New.class) @RequestBody BorrowerDto dto) {
        return borrowerService.fill(dto);
    }*/

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
