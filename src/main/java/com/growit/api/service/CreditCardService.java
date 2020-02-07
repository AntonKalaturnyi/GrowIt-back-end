package com.growit.api.service;

import com.growit.api.domain.CreditCard;
import com.growit.api.repo.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final CreditCardRepo creditCardRepo;

    @Autowired
    public CreditCardService(CreditCardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    public CreditCard create(CreditCard card) {
        return creditCardRepo.save(card);
    }
}
