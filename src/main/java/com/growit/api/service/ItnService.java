package com.growit.api.service;

import com.growit.api.domain.ITN;
import com.growit.api.repo.ItnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItnService {

    private final ItnRepo itnRepo;
    private final BorrowerService borrowerService;

    @Autowired
    public ItnService(ItnRepo itnRepo, BorrowerService borrowerService) {
        this.itnRepo = itnRepo;
        this.borrowerService = borrowerService;
    }

    public ITN create(ITN itn) {
// mapper
/*        ITN fromDb = itnRepo.save(itn);

        borrowerService.add
        return fromDb;*/
        return null;
    }
}
