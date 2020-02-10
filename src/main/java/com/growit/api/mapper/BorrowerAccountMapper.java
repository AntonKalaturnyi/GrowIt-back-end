package com.growit.api.mapper;

import com.growit.api.domain.BorrowerAccount;
import com.growit.api.dto.BorrowerAccountDto;
import com.growit.api.repo.BorrowerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class BorrowerAccountMapper extends AbstractMapper<BorrowerAccount, BorrowerAccountDto> {

    private final ModelMapper mapper;
    private final BorrowerRepo borrowerRepo;

    @Autowired
    BorrowerAccountMapper(ModelMapper mapper, BorrowerRepo borrowerRepo) {
        super(BorrowerAccount.class, BorrowerAccountDto.class);
        this.mapper = mapper;
        this.borrowerRepo = borrowerRepo;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(BorrowerAccount.class, BorrowerAccountDto.class)
                .addMappings(m -> m.skip(BorrowerAccountDto::setBorrowerId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(BorrowerAccountDto.class, BorrowerAccount.class)
                .addMappings(m -> m.skip(BorrowerAccount::setBorrower)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(BorrowerAccount source, BorrowerAccountDto destination) {
        destination.setBorrowerId(source.getBorrower().getId());
    }


    @Override
    void mapSpecificFields(BorrowerAccountDto source, BorrowerAccount destination) {
        destination.setBorrower(borrowerRepo.findById(source.getBorrowerId()).get());
    }
}
