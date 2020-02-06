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

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Borrower.class, BorrowerDto.class)
                .addMappings(m -> m.skip(BorrowerDto::setCreditHistoryId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BorrowerDto::setWorkSphereString)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BorrowerDto::setHomeOwnershipString)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(BorrowerDto.class, Borrower.class)
                .addMappings(m -> m.skip(Borrower::setCreditHistory)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Borrower::setWorkSphere)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Borrower::setHomeOwnership)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Borrower source, BorrowerDto destination) {
        destination.setCreditHistoryId(getCreditHistoryId(source));
        destination.setWorkSphereString(getWorkSphereString(source));
        destination.setHomeOwnershipString(getWorkSphereString(source));
    }

    @Override
    void mapSpecificFields(BorrowerDto source, Borrower destination) {
        destination.setCreditHistory(creditHistoryRepo.findById(source.getCreditHistoryId()).orElse(null));
        destination.setWorkSphere(workSphereRepo.findBySphereEngLike(source.getWorkSphereString()));
        destination.setHomeOwnership(homeOwnershipRepo.findByHomeOwnershipEngLike(source.getHomeOwnershipString()));

    }

    private Long getCreditHistoryId(Borrower source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCreditHistory().getId();
    }

    private String getWorkSphereString(Borrower source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getWorkSphere().getSphereEng();
    }

    private String getHomeOwnershipString(Borrower source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getHomeOwnership().getHomeOwnershipEng();
    }
}

