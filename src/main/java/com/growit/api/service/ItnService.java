package com.growit.api.service;

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

/* =No need=
    @Transactional
    public ITN createInvestorItn(InvestorPassportAndItnDto dto) {
        ITN itn = new ITN();
        itn.setITN_digits(dto.getItnNumber());
        return itnRepo.save(itn);
    }*/
}
