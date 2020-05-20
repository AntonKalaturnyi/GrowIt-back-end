package com.growit.api.controller;

import com.growit.api.domain.Card;
import com.growit.api.dto.New;
import com.growit.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService creditCardService;

    @Autowired
    public CardController(CardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card addCart(@Validated(New.class) @RequestBody Card card) {
        return creditCardService.create(card);
    }

}
