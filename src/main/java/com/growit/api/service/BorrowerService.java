package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.AuthDto;
import com.growit.api.dto.BorrowerDto;
import com.growit.api.dto.ItnDto;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.mapper.BorrowerMapper;
import com.growit.api.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;

@Service
public class BorrowerService implements UserDetailsService {

    private final BorrowerRepo borrowerRepo;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final WorkSphereRepo workSphereRepo;
    private final CreditHistoryRepo creditHistoryRepo;
    private final ContactPersonRepo contactPersonRepo;
    private final InvestorRepo investorRepo;
    private final HomeOwnershipRepo homeOwnershipRepo;
    private final PasswordEncoder passwordEncoder;
    private final CreditCardRepo creditCardRepo;
    private final BorrowerMapper mapper;

    @Autowired
    public BorrowerService(BorrowerRepo borrowerRepo, BorrowerAccountRepo borrowerAccountRepo, WorkSphereRepo workSphereRepo,
                           CreditHistoryRepo creditHistoryRepo, ContactPersonRepo contactPersonRepo,
                           InvestorRepo investorRepo, HomeOwnershipRepo homeOwnershipRepo, PasswordEncoder passwordEncoder, CreditCardRepo creditCardRepo,
                           BorrowerMapper mapper) {
        this.borrowerRepo = borrowerRepo;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.workSphereRepo = workSphereRepo;
        this.creditHistoryRepo = creditHistoryRepo;
        this.contactPersonRepo = contactPersonRepo;
        this.investorRepo = investorRepo;
        this.homeOwnershipRepo = homeOwnershipRepo;
        this.passwordEncoder = passwordEncoder;
        this.creditCardRepo = creditCardRepo;
        this.mapper = mapper;
    }

    public UserRegistrationDto create(UserRegistrationDto dto) {
        Borrower borrower = borrowerRepo.save(new Borrower(dto));
        BorrowerAccount account = borrowerAccountRepo.save(new BorrowerAccount());
        UserService.setRegisteredUserRole(borrower);
        borrower.setPassword(passwordEncoder.encode(dto.getPassword()));
        borrower.setAge(Period.between(borrower.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        borrower.setLastVisit(LocalDateTime.now());
        borrower.setActive(true);
        account.setBorrower(borrower);
        account = borrowerAccountRepo.save(account);
        borrower.setBorrowerAccount(account);
        return new UserRegistrationDto(borrowerRepo.save(borrower));
    }

    public BorrowerDto fill(BorrowerDto dto) {
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
        borrower.setKidsBefore18yo(dto.getKidsBefore18yo());
        borrower.setKidsAfter18yo(dto.getKidsAfter18yo());
        borrower.setHomePhone(dto.getHomePhone());
        borrower.setWorkPhone(dto.getWorkPhone());
        borrower.setFax(dto.getFax());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        borrower.setWorkType(dto.getWorkType());
        borrower.setSpouseITN(dto.getSpouseITN());
        borrower.setEDRPOUcode(dto.getEDRPOUcode());
        borrower.setJobTitle(dto.getJobTitle());
        return borrower;
    }

    void addItn(ItnDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getUserId()).get();
        //    borrower.setItn(dto); //mapper
        borrowerRepo.save(borrower);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return borrowerRepo.findByEmail(email);
    }

    public UserRegistrationDto createWithCredentials(AuthDto creds) {
        Borrower borrower;
        Investor investor = investorRepo.findByEmail(creds.getUsername());

        borrower = new Borrower();

        if (investor != null) {
            borrower = new Borrower(investor);
            borrower.setActive(investor.isActive());
            borrower.setBirthday(investor.getBirthday());
            borrower.setGender(investor.getGender());
            borrower.setAge(Period.between(borrower.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
            borrower.setName(investor.getName());
            borrower.setMiddleName(investor.getMiddleName());
            borrower.setLastName(investor.getLastName());
            borrower.setLastVisit(LocalDateTime.now());
            borrower.setPhone(investor.getPhone());
            borrower.setUserpic(investor.getUserpic());
        }

        borrower.setEmail(creds.getUsername());
        borrower.setPassword(passwordEncoder.encode(creds.getPassword()));
        UserService.setRegisteredUserRole(borrower);
        borrower.setActive(true); // add email activation for this
        return new UserRegistrationDto(borrowerRepo.save(borrower));
    }
}
