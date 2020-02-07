package com.growit.api.service;

import com.growit.api.domain.HomeOwnership;
import com.growit.api.repo.HomeOwnershipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeOwnershipService {

    private final HomeOwnershipRepo homeOwnershipRepo;

    @Autowired
    public HomeOwnershipService(HomeOwnershipRepo homeOwnershipRepo) {
        this.homeOwnershipRepo = homeOwnershipRepo;
    }

    public HomeOwnership create(HomeOwnership homeOwnership) {
        return homeOwnershipRepo.save(homeOwnership);
    }

}
