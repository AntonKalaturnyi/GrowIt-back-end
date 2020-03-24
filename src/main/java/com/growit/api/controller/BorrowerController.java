package com.growit.api.controller;

import com.growit.api.domain.Borrower;
import com.growit.api.dto.*;
import com.growit.api.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    public Integer fillInvestorAndSendSms(@AuthenticationPrincipal Borrower borrower, @Validated(New.class) @RequestBody BorrowerRegDto dto) {
        return borrowerService.fillPersonalInfoAndSendSmsCode(borrower, dto);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/reg-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerRegDto getRegistrationData(@AuthenticationPrincipal Borrower borrower) {
        if (borrower.getPhone() == null) {
            return new BorrowerRegDto();
        }
        return borrowerService.getRegData(borrower);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/save-passport-itn", consumes = {"multipart/form-data"})
    public Boolean saveBorrowerPassportAndItn(@RequestPart("dto") @Validated(New.class) BorrowerPassportAndItnDto dto,
                                              @RequestPart("file") @NotNull @NotBlank MultipartFile file, @AuthenticationPrincipal Borrower borrower) {
        return borrowerService.savePassportAndItn(dto, borrower, file);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/docs-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowerPassportAndItnDto getDocumentsData(@AuthenticationPrincipal Borrower borrower) {
        return borrower.getPassport() != null ? borrowerService.getDocsData(borrower) : new BorrowerPassportAndItnDto();
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/set-address",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean saveLivingAddress(@AuthenticationPrincipal Borrower borrower, @Validated(New.class) @RequestBody AddressDto dto) {
        return borrowerService.saveLivingAddress(borrower, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/address-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto getAddressData(@AuthenticationPrincipal Borrower borrower) {
        return borrower.getAddress() != null ? borrowerService.getAddrData(borrower) : new AddressDto();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/set-employment",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean saveEmploymentInfo(@AuthenticationPrincipal Borrower borrower, @Validated(New.class) @RequestBody EmploymentDto dto) {
        return borrowerService.handleEmployment(borrower, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/employment-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmploymentDto getEmploymentData(@AuthenticationPrincipal Borrower borrower) {
        return borrower.getEmployment() != null ? borrowerService.getEmploymentData(borrower) : new EmploymentDto();
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/set-education",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean saveEducationInfo(@AuthenticationPrincipal Borrower borrower, @Validated(New.class) @RequestBody EducationDto dto) {
        return borrowerService.handleEducation(borrower, dto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/set-assets",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean saveAssetsInfo(@AuthenticationPrincipal Borrower borrower, @Validated(New.class) @RequestBody AssetsDto dto) {
        return borrowerService.handleAssets(borrower, dto);
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
