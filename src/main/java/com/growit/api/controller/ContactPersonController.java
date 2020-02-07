package com.growit.api.controller;

import com.growit.api.dto.ContactPersonDto;
import com.growit.api.dto.New;
import com.growit.api.repo.ContactPersonRepo;
import com.growit.api.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact-person")
public class ContactPersonController {

    private final ContactPersonService contactPersonService;
    private final ContactPersonRepo contactPersonRepo;

    @Autowired
    public ContactPersonController(ContactPersonService contactPersonService, ContactPersonRepo contactPersonRepo) {
        this.contactPersonService = contactPersonService;
        this.contactPersonRepo = contactPersonRepo;
    }

    //    @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerCreateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContactPersonDto create(@Validated(New.class) @RequestBody ContactPersonDto dto) {
        return contactPersonService.create(dto);
    }

}
