package com.growit.api.controller;

import com.growit.api.domain.Loan;
import com.growit.api.dto.LoanDto;
import com.growit.api.dto.New;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {


    //    @PreAuthorize("hasAnyRole" +
//            "(@securityConfiguration.getTaskControllerCreateTaskAllowedRoles())")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, path = "/create")
    public Loan createTask(@Validated(New.class) @RequestBody LoanDto loanDto) {
//        TaskDto dto = taskDto;
//        return taskService.create(taskDto);
        return null;
    }

}
