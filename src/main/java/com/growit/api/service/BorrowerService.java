package com.growit.api.service;

import com.growit.api.domain.Borrower;
import com.growit.api.domain.BorrowerAccount;
import com.growit.api.domain.Investor;
import com.growit.api.domain.User;
import com.growit.api.dto.*;
import com.growit.api.mapper.BorrowerMapper;
import com.growit.api.repo.*;
import com.growit.api.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class BorrowerService implements UserDetailsService {

    private final BorrowerRepo borrowerRepo;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final SocialStatusRepo socialStatusRepo;
    private final AddressService addressService;
    private final EmploymentService employmentService;
    private final InvestorRepo investorRepo;
    private final HomeOwnershipRepo homeOwnershipRepo;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final PassportService passportService;
    private final EducationService educationService;
    private final BorrowerMapper mapper;

    @Autowired
    public BorrowerService(BorrowerRepo borrowerRepo, BorrowerAccountRepo borrowerAccountRepo, CreditHistoryRepo creditHistoryRepo,
                           ContactPersonRepo contactPersonRepo, SocialStatusRepo socialStatusRepo, AddressService addressService,
                           EmploymentService employmentService, InvestorRepo investorRepo, HomeOwnershipRepo homeOwnershipRepo,
                           AuthService authService, PasswordEncoder passwordEncoder, PassportService passportService,
                           CreditCardRepo creditCardRepo, EducationService educationService, BorrowerMapper mapper) {
        this.borrowerRepo = borrowerRepo;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.socialStatusRepo = socialStatusRepo;
        this.addressService = addressService;
        this.employmentService = employmentService;
        this.investorRepo = investorRepo;
        this.homeOwnershipRepo = homeOwnershipRepo;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.passportService = passportService;
        this.educationService = educationService;
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

/*    @Transactional
    public BorrowerDto fill(BorrowerDto dto) {
*//**  + creditCard(service) /Passport(controller + service)  /ITN/Address
 =Verification apart post*//*
        return mapper.toDto(borrowerRepo.save(fillBorrower(dto))); // +mapper
    }

    private Borrower fillBorrower(BorrowerDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        setBorrowerDtoFields(borrower, dto);
        borrower.setHomeOwnership(homeOwnershipRepo.findByHomeOwnershipEngLike(dto.getHomeOwnershipString()));
        borrower.setMonthlyIncomeTotal(borrower.getMonthlyIncomeOfficial() + borrower.getMonthlyIncomeAdditional());
        return borrower;
    }*/

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

        borrower.setEmail(creds.getUsername());
        borrower.setActive(true); // add email activation for this
        borrower.setPassword(passwordEncoder.encode(creds.getPassword()));
        borrower.setLastVisit(LocalDateTime.now());
        UserService.setRegisteredUserRole(borrower);
        borrowerRepo.save(borrower);
        return authService.signIn(creds);
    }

    @Transactional
    public BorrowerUpdateDto updateBorrower(BorrowerUpdateDto dto) {
        Borrower borrower = setFromDto(dto);
/*        setBorrowerDtoFields(borrower, dto);

        if (!dto.getHomeOwnershipString().equals(borrower.getHomeOwnership().getHomeOwnershipEng())) {
            borrower.setHomeOwnership(homeOwnershipRepo.findByHomeOwnershipEngLike(dto.getHomeOwnershipString()));
        }
        return new BorrowerUpdateDto(borrowerRepo.save(borrower));*/

        return null;
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
        borrower.setKidsBefore18yo(dto.getKidsBefore18yo());
        borrower.setKidsAfter18yo(dto.getKidsAfter18yo());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        borrower.setWorkType(dto.getWorkType());
        borrower.setEDRPOUcode(dto.getEDRPOUcode());
        borrower.setJobTitle(dto.getJobTitle());
        return borrower;
    }

    public Integer fillPersonalInfoAndSendSmsCode(Borrower borrower, BorrowerRegDto dto) {
        UserService.setUserFields(borrower, dto);
        borrower.setMaritalStatus(dto.getMaritalStatus());
        borrower.setKidsBefore18yo(dto.getKidsBefore18yo());
        borrower.setKidsAfter18yo(dto.getKidsAfter18yo());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        borrower.setRegistered(true);
        BorrowerAccount account = borrowerAccountRepo.save(new BorrowerAccount());
        account.setBorrower(borrower);
        account = borrowerAccountRepo.save(account);
        borrower.setBorrowerAccount(account);
        borrowerRepo.save(borrower);
        return ConstantUtil.getRandom6DigitNumber();
    }

    @Transactional
    public Boolean savePassportAndItn(BorrowerPassportAndItnDto dto, Borrower borrower, MultipartFile file) {
        borrower.setPassport(passportService.createBorrowerPass(dto, file));
        borrower.setItn(dto.getItnNumber());
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    public Boolean saveLivingAddress(Borrower borrower, AddressDto dto) {
        if (dto.isSameAddressInPassport()) {
            borrower.setAddress(borrower.getPassport().getAddressOfRegistration());
            borrowerRepo.save(borrower);
            return true;
        }
        borrower.setAddress(addressService.addressFromAddressDto(dto));
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Boolean handleEmployment(Borrower borrower, EmploymentDto dto) {
        borrower.setSocialStatus(socialStatusRepo.findByStatusUaLike(dto.getSocialStatus()));
        borrower.setEmployment(employmentService.create(dto));
        borrower.setMonthlyIncomeOfficial(dto.getMonthlyIncomeOfficial());
        borrower.setMonthlyIncomeAdditional(dto.getMonthlyIncomeAdditional());
        borrower.setAdditionalIncomeSource(dto.getAdditionalIncomeSource());
        borrower.setMonthlyExpenses(dto.getMonthlyExpenses());
        borrower.setScholarship(dto.getScholarship());
        borrower.setPension(dto.getPension());
        borrower.setEmployeesCount(dto.getEmployeesCount());
        borrower.setMonthlyObligations(dto.getMonthlyObligations());
        borrower.setMonthlyIncomeTotal(borrower.getMonthlyIncomeOfficial() + borrower.getMonthlyIncomeAdditional());
        System.out.println("===Saving: " + borrower.getEmail() + "===");
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Boolean handleEducation(Borrower borrower, EducationDto dto) {
        borrower.setEducation(educationService.create(dto));
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Boolean handleAssets(Borrower borrower, AssetsDto dto) {
        borrower.setFlat(dto.getFlat());
        borrower.setHasHouse(dto.isHasHouse());
        borrower.setHasCar(dto.isHasCar());
        if (borrower.getHasCar()) {
            borrower.setCarAge(dto.getCarAge());
        }
        borrower.setWasAbroad(dto.isWasAbroad());
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public BorrowerRegDto getRegData(Borrower borrower) {
        return new BorrowerRegDto(
                borrower.getName(),
                borrower.getLastName(),
                borrower.getMiddleName(),
                borrower.getGender(),
                borrower.getBirthday(),
                borrower.getPhone().substring(4),
                borrower.getMaritalStatus(),
                borrower.getKidsBefore18yo(),
                borrower.getKidsAfter18yo(),
                borrower.getInstagram(),
                borrower.getFacebook()
        );
    }
}
