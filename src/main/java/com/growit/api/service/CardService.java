package com.growit.api.service;

import com.growit.api.domain.Card;
import com.growit.api.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepo creditCardRepo;

    @Autowired
    public CardService(CardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    // @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Card create(Card card) {
        return creditCardRepo.save(card);
    }
}
