package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.*;
import com.growit.api.mapper.BorrowerMapper;
import com.growit.api.repo.*;
import com.growit.api.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;

@Service
public class BorrowerService implements UserDetailsService {

    private final BorrowerRepo borrowerRepo;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final WorkSphereRepo workSphereRepo;
    private final CreditHistoryRepo creditHistoryRepo;
    private final ContactPersonRepo contactPersonRepo;
    private final InvestorRepo investorRepo;
    private final HomeOwnershipRepo homeOwnershipRepo;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final CreditCardRepo creditCardRepo;
    private final BorrowerMapper mapper;

    @Autowired
    public BorrowerService(BorrowerRepo borrowerRepo, BorrowerAccountRepo borrowerAccountRepo, WorkSphereRepo workSphereRepo,
                           CreditHistoryRepo creditHistoryRepo, ContactPersonRepo contactPersonRepo,
                           InvestorRepo investorRepo, HomeOwnershipRepo homeOwnershipRepo, AuthService authService, PasswordEncoder passwordEncoder, CreditCardRepo creditCardRepo,
                           BorrowerMapper mapper) {
        this.borrowerRepo = borrowerRepo;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.workSphereRepo = workSphereRepo;
        this.creditHistoryRepo = creditHistoryRepo;
        this.contactPersonRepo = contactPersonRepo;
        this.investorRepo = investorRepo;
        this.homeOwnershipRepo = homeOwnershipRepo;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.creditCardRepo = creditCardRepo;
        this.mapper = mapper;
    }

    @Transactional
    public UserRegistrationDto fillBasicInfo(UserRegistrationDto dto) {
        Borrower borrower = setFromDto(dto);
        borrower.setBirthday(dto.getBirthday());
        BorrowerAccount account = borrowerAccountRepo.save(new BorrowerAccount());
        UserService.setRegisteredUserRole(borrower);
        account.setBorrower(borrower);
        account = borrowerAccountRepo.save(account);
        borrower.setBorrowerAccount(account);
        return new UserRegistrationDto(borrowerRepo.save(borrower));
    }

    @Transactional
    public BorrowerDto fill(BorrowerDto dto) {
/**  + creditCard(service) /Passport(controller + service)  /ITN/Address
 =Verification apart post*/
        return mapper.toDto(borrowerRepo.save(fillBorrower(dto))); // +mapper
    }

    private Borrower fillBorrower(BorrowerDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        setBorrowerDtoFields(borrower, dto);
        borrower.setWorkSphere(workSphereRepo.findBySphereEngLike(dto.getWorkSphereString()));
        borrower.setHomeOwnership(homeOwnershipRepo.findByHomeOwnershipEngLike(dto.getHomeOwnershipString()));
        borrower.setMonthlyIncomeTotal(borrower.getMonthlyIncomeOfficial() + borrower.getMonthlyIncomeAdditional());
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

    @Transactional
    public ResponseEntity createWithCredentials(AuthDto creds) {
        Borrower borrower;
        Investor investor = investorRepo.findByEmail(creds.getUsername());

        borrower = new Borrower();

        if (investor != null) {
            borrower = new Borrower(investor);
        }

        borrower.setActive(true); // add email activation for this
        borrower.setEmail(creds.getUsername());
        borrower.setPassword(passwordEncoder.encode(creds.getPassword()));
        borrower.setLastVisit(LocalDateTime.now());
        UserService.setRegisteredUserRole(borrower);
        borrowerRepo.save(borrower);
        return authService.signIn(creds);
    }

    @Transactional
    public BorrowerUpdateDto updateBorrower(BorrowerUpdateDto dto) {
        Borrower borrower = setFromDto(dto);
        setBorrowerDtoFields(borrower, dto);
        if (!dto.getWorkSphereString().equals(borrower.getWorkSphere().getSphereEng())) {
            borrower.setWorkSphere(workSphereRepo.findBySphereEngLike(dto.getWorkSphereString()));
        }
        if (!dto.getHomeOwnershipString().equals(borrower.getHomeOwnership().getHomeOwnershipEng())) {
            borrower.setHomeOwnership(homeOwnershipRepo.findByHomeOwnershipEngLike(dto.getHomeOwnershipString()));
        }
        return new BorrowerUpdateDto(borrowerRepo.save(borrower));
    }

    private Borrower setFromDto(UserRegistrationDto dto) {
        Borrower borrower = borrowerRepo.findByEmail(dto.getEmail());
        borrower.setName(dto.getName());
        borrower.setMiddleName(dto.getMiddleName());
        borrower.setLastName(dto.getLastName());
        borrower.setGender(dto.getGender());
        borrower.setUserpic(dto.getUserpic());
        borrower.setPhone(dto.getPhone());
        borrower.setLastVisit(LocalDateTime.now());
        borrower.setUpdated(borrower.getLastVisit());
        borrower.setAge(Period.between(borrower.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        return borrower;
    }

    private Borrower setBorrowerDtoFields(Borrower borrower, BorrowerTransferObject dto) {

        borrower.setMonthlyIncomeOfficial(dto.getMonthlyIncomeOfficial());
        borrower.setMonthlyIncomeAdditional(dto.getMonthlyIncomeAdditional());
        borrower.setMarried(dto.isMarried());
        borrower.setDivorced(dto.isDivorced());
        borrower.setKidsBefore18yo(dto.getKidsBefore18yo());
        borrower.setKidsAfter18yo(dto.getKidsAfter18yo());
        borrower.setHomePhone(dto.getHomePhone());
        borrower.setWorkPhone(dto.getWorkPhone());
        borrower.setFax(dto.getFax());
        borrower.setSpouseITN(dto.getSpouseITN());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        borrower.setWorkType(dto.getWorkType());
        borrower.setEDRPOUcode(dto.getEDRPOUcode());
        borrower.setJobTitle(dto.getJobTitle());
        return borrower;
    }

    public Integer fillPersonalInfoAndSendSmsCode(BorrowerRegDto dto) {
        Borrower borrower = borrowerRepo.findByEmail(dto.getEmail());
        borrower.setName(dto.getName());
        borrower.setMiddleName(dto.getMiddleName());
        borrower.setLastName(dto.getLastName());
        borrower.setGender(dto.getGender());
        borrower.setBirthday(dto.getBirthday());
        borrower.setPhone(dto.getPhone());
        borrower.setAge(Period.between(borrower.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        borrower.setLastVisit(LocalDateTime.now());
        borrower.setActive(true);
        borrower.setMaritalStatus(dto.getMaritalStatus());
        borrower.setKidsBefore18yo(dto.getKidsBefore18yo());
        borrower.setKidsAfter18yo(dto.getKidsAfter18yo());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        BorrowerAccount account = borrowerAccountRepo.save(new BorrowerAccount());
        account.setBorrower(borrower);
        account = borrowerAccountRepo.save(account);
        borrower.setBorrowerAccount(account);
        borrowerRepo.save(borrower);
        return ConstantUtil.getRandom6DigitNumber();
    }
}
