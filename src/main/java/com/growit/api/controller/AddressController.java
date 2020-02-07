package com.growit.api.controller;

import com.growit.api.dto.AddressDto;
import com.growit.api.dto.New;
import com.growit.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto addAddress(@Validated(New.class) @RequestBody AddressDto dto) {
        return addressService.create(dto);
    }
}
