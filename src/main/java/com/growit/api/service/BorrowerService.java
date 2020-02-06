package com.growit.api.service;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.BorrowerAccount;
import com.growit.api.dto.BorrowerDto;
import com.growit.api.dto.ItnDto;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.mapper.BorrowerMapper;
import com.growit.api.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class BorrowerService {

    private final BorrowerRepo borrowerRepo;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final WorkSphereRepo workSphereRepo;
    private final CreditHistoryRepo creditHistoryRepo;
    private final ContactPersonRepo contactPersonRepo;
    private final HomeOwnershipRepo homeOwnershipRepo;
    private final PasswordEncoder passwordEncoder;
    private final CreditCardRepo creditCardRepo;
    private final BorrowerMapper mapper;

    @Autowired
    public BorrowerService(BorrowerRepo borrowerRepo, BorrowerAccountRepo borrowerAccountRepo, WorkSphereRepo workSphereRepo,
                           CreditHistoryRepo creditHistoryRepo, ContactPersonRepo contactPersonRepo,
                           HomeOwnershipRepo homeOwnershipRepo, PasswordEncoder passwordEncoder, CreditCardRepo creditCardRepo,
                           BorrowerMapper mapper) {
        this.borrowerRepo = borrowerRepo;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.workSphereRepo = workSphereRepo;
        this.creditHistoryRepo = creditHistoryRepo;
        this.contactPersonRepo = contactPersonRepo;
        this.homeOwnershipRepo = homeOwnershipRepo;
        this.passwordEncoder = passwordEncoder;
        this.creditCardRepo = creditCardRepo;
        this.mapper = mapper;
    }

    public UserRegistrationDto create(UserRegistrationDto dto) {
        Borrower borrower = borrowerRepo.save(new Borrower(dto));
        BorrowerAccount account = borrowerAccountRepo.save(new BorrowerAccount());
        borrower.setPassword(passwordEncoder.encode(dto.getPassword()));
        borrower.setAge(Period.between(borrower.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        borrower.setActive(true);
        account.setBorrower(borrower);
        account = borrowerAccountRepo.save(account);
        borrower.setBorrowerAccount(account);
        return new UserRegistrationDto(borrowerRepo.save(borrower));
    }

    public BorrowerDto fill(BorrowerDto dto) {
/*        Set<ContactPerson> personSet = new HashSet<>();
        for (long id: dto.getContactPersonsIds()) {
            personSet.add( contactPersonRepo.findById(id).get());
        }
        borrower.setContactPersons(personSet);
        borrower.setHomeOwnership(homeOwnershipRepo.findById(dto.getHomeOwnershipId()).get()); // itn/homeOwnership/creditCard controller
        borrower.setCreditCard( creditCardRepo.findById(dto.getCreditCardId()).get() );*/
/**  + creditCard(service) /Passport(controller + service)  /ITN/Address
 =Verification apart post*/
        return mapper.toDto(borrowerRepo.save(fillBorrower(dto))); // +mapper
    }

    private Borrower fillBorrower(BorrowerDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        borrower.setWorkSphere(workSphereRepo.findBySphereEngLike(dto.getWorkSphereString()));
        borrower.setCreditHistory(creditHistoryRepo.findById(dto.getCreditHistoryId()).get());
        borrower.setMonthlyIncomeOfficial(dto.getMonthlyIncomeOfficial());
        borrower.setMonthlyIncomeAdditional(dto.getMonthlyIncomeAdditional());
        borrower.setMonthlyIncomeTotal(borrower.getMonthlyIncomeOfficial() + borrower.getMonthlyIncomeAdditional());
        borrower.setHomeOwnership(homeOwnershipRepo.findByHomeOwnershipEngLike(dto.getHomeOwnershipString()));
        borrower.setMarried(dto.isMarried());
        borrower.setKids(dto.getKids());
        borrower.setHomePhone(dto.getHomePhone());
        borrower.setWorkPhone(dto.getWorkPhone());
        borrower.setFax(dto.getFax());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        borrower.setWorkType(dto.getWorkType());
        borrower.setSpouseITN(dto.getSpouseITN());
        borrower.setPhotoWithPassport(dto.getPhotoWithPassport());
        borrower.setEDRPOUcode(dto.getEDRPOUcode());
        borrower.setJobTitle(dto.getJobTitle());
        return borrower;
    }

    void addItn(ItnDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getUserId()).get();
        //    borrower.setItn(dto); //mapper
        borrowerRepo.save(borrower);
    }
}
