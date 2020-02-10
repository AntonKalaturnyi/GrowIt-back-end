package com.growit.api.mapper;

import com.growit.api.domain.Loan;
import com.growit.api.dto.LoanDto;
import com.growit.api.repo.BorrowerRepo;
import com.growit.api.repo.LoanPurposeRepo;
import com.growit.api.repo.LoanStatusRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class LoanMapper extends AbstractMapper<Loan, LoanDto> {

    private final ModelMapper mapper;
    private final LoanPurposeRepo loanPurposeRepo;
    private final LoanStatusRepo loanStatusRepo;
    private final BorrowerRepo borrowerRepo;


    @Autowired
    public LoanMapper(ModelMapper mapper, LoanPurposeRepo loanPurposeRepo, LoanStatusRepo loanStatusRepo, BorrowerRepo borrowerRepo) {
        super(Loan.class, LoanDto.class);
        this.mapper = mapper;
        this.loanPurposeRepo = loanPurposeRepo;
        this.loanStatusRepo = loanStatusRepo;
        this.borrowerRepo = borrowerRepo;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Loan.class, LoanDto.class)
                .addMappings(m -> m.skip(LoanDto::setLoanPurpose)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(LoanDto::setLoanStatus)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(LoanDto::setBorrowerId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(LoanDto.class, Loan.class)
                .addMappings(m -> m.skip(Loan::setLoanPurpose)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Loan::setStatus)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Loan::setBorrower)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Loan source, LoanDto destination) {
        destination.setLoanPurpose(getLoanPurposeString(source));
        destination.setLoanStatus(getLoanStatusString(source));
        destination.setBorrowerId(getBorrowerId(source));
    }


    @Override
    void mapSpecificFields(LoanDto source, Loan destination) {
        destination.setLoanPurpose(loanPurposeRepo.findByPurposeEng(source.getLoanPurpose()));
        destination.setStatus(loanStatusRepo.findByStatusEng(source.getLoanStatus()));
        destination.setBorrower(borrowerRepo.findById(source.getBorrowerId()).get());
    }

    private String getLoanPurposeString(Loan source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLoanPurpose().getPurposeEng();
    }

    private String getLoanStatusString(Loan source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getStatus().getStatusEng();
    }

    private long getBorrowerId(Loan source) {
        return Objects.isNull(source) || Objects.isNull(source.getBorrower()) ? null : source.getBorrower().getId();
    }
}

