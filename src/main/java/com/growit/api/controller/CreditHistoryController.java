package com.growit.api.controller;

import com.growit.api.dto.CreditHistoryDto;
import com.growit.api.dto.New;
import com.growit.api.service.CreditHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-history")
public class CreditHistoryController {

    private final CreditHistoryService creditHistoryService;

    @Autowired
    public CreditHistoryController(CreditHistoryService creditHistoryService) {
        this.creditHistoryService = creditHistoryService;
    }


    //    @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerCreateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditHistoryDto createTask(@Validated(New.class) @RequestBody CreditHistoryDto dto) {
        return creditHistoryService.create(dto);
    }

}
