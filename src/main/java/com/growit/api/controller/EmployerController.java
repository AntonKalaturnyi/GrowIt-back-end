package com.growit.api.controller;

import com.growit.api.dto.EmploymentDto;
import com.growit.api.dto.New;
import com.growit.api.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    private final EmploymentService employerService;

    @Autowired
    public EmployerController(EmploymentService employerService) {
        this.employerService = employerService;
    }

    //    @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerCreateTaskAllowedRoles())")
/*    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmploymentDto create(@Validated(New.class) @RequestBody EmploymentDto dto) {
        return employerService.create(dto);
    }*/

}
