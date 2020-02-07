package com.growit.api.controller;

import com.growit.api.domain.HomeOwnership;
import com.growit.api.dto.New;
import com.growit.api.service.HomeOwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeOwnershipController {

    private final HomeOwnershipService homeOwnershipService;

    @Autowired
    public HomeOwnershipController(HomeOwnershipService homeOwnershipService) {
        this.homeOwnershipService = homeOwnershipService;
    }

    //    @PreAuthorize("hasAnyRole" + "(@securityConfiguration.getTaskControllerCreateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HomeOwnership create(@Validated(New.class) @RequestBody HomeOwnership homeOwnership) {
        return homeOwnershipService.create(homeOwnership);
    }

}
