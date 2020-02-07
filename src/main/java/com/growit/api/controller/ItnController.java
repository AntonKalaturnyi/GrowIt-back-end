package com.growit.api.controller;

import com.growit.api.domain.ITN;
import com.growit.api.dto.New;
import com.growit.api.service.ItnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itn")
public class ItnController {

    private final ItnService itnService;

    @Autowired
    public ItnController(ItnService itnService) {
        this.itnService = itnService;
    }

    //    @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerCreateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ITN addItn(@Validated(New.class) @RequestBody ITN itn) {
        return itnService.create(itn);
    }

}
