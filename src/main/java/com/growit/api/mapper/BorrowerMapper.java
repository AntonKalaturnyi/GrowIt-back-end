package com.growit.api.mapper;

import com.growit.api.domain.Borrower;
import com.growit.api.dto.BorrowerDto;
import com.growit.api.repo.CreditHistoryRepo;
import com.growit.api.repo.HomeOwnershipRepo;
import com.growit.api.repo.WorkSphereRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class BorrowerMapper extends AbstractMapper<Borrower, BorrowerDto> {

    private final ModelMapper mapper;
    private final CreditHistoryRepo creditHistoryRepo;
    private final WorkSphereRepo workSphereRepo;
    private final HomeOwnershipRepo homeOwnershipRepo;

    @Autowired
    public BorrowerMapper(ModelMapper mapper, CreditHistoryRepo creditHistoryRepo, WorkSphereRepo workSphereRepo, HomeOwnershipRepo homeOwnershipRepo) {
        super(Borrower.class, BorrowerDto.class);
        this.creditHistoryRepo = creditHistoryRepo;
        this.workSphereRepo = workSphereRepo;
        this.homeOwnershipRepo = homeOwnershipRepo;
        this.mapper = mapper;
    }


}

