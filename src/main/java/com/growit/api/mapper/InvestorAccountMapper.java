/*
package com.growit.api.mapper;

import com.growit.api.domain.InvestorAccount;
import com.growit.api.dto.InvestorAccountDto;
import com.growit.api.repo.InvestorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class InvestorAccountMapper extends AbstractMapper<InvestorAccount, InvestorAccountDto> {

    private final ModelMapper mapper;
    private final InvestorRepo investorRepo;

    @Autowired
    InvestorAccountMapper(ModelMapper mapper, InvestorRepo investorRepo) {
        super(InvestorAccount.class, InvestorAccountDto.class);
        this.mapper = mapper;
        this.investorRepo = investorRepo;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(InvestorAccount.class, InvestorAccountDto.class)
                .addMappings(m -> m.skip(InvestorAccountDto::setInvestorId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(InvestorAccountDto.class, InvestorAccount.class)
                .addMappings(m -> m.skip(InvestorAccount::setInvestor)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(InvestorAccount source, InvestorAccountDto destination) {
        destination.setInvestorId(source.getInvestor().getId());
    }


    @Override
    void mapSpecificFields(InvestorAccountDto source, InvestorAccount destination) {
        destination.setInvestor(investorRepo.findById(source.getInvestorId()).get());
    }
}
*/
