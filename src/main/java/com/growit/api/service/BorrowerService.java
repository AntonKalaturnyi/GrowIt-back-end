package com.growit.api.service;

import com.growit.api.domain.*;
import com.growit.api.dto.*;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BorrowerService implements UserDetailsService {

    private final BorrowerRepo borrowerRepo;
    private final BorrowerAccountRepo borrowerAccountRepo;
    private final SocialStatusRepo socialStatusRepo;
    private final AddressService addressService;
    private final EmploymentService employmentService;
    private final InvestorRepo investorRepo;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final PassportService passportService;
    private final EducationService educationService;
    private final ContactPersonRepo contactPersonRepo;

    @Autowired
    public BorrowerService(BorrowerRepo borrowerRepo, BorrowerAccountRepo borrowerAccountRepo, CreditHistoryRepo creditHistoryRepo,
                           ContactPersonRepo contactPersonRepo, SocialStatusRepo socialStatusRepo, AddressService addressService,
                           EmploymentService employmentService, InvestorRepo investorRepo,
                           AuthService authService, PasswordEncoder passwordEncoder, PassportService passportService,
                           CreditCardRepo creditCardRepo, EducationService educationService) {
        this.borrowerRepo = borrowerRepo;
        this.borrowerAccountRepo = borrowerAccountRepo;
        this.socialStatusRepo = socialStatusRepo;
        this.addressService = addressService;
        this.employmentService = employmentService;
        this.investorRepo = investorRepo;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.passportService = passportService;
        this.educationService = educationService;
        this.contactPersonRepo = contactPersonRepo;
    }

    @Transactional
    public UserRegistrationDto fillBasicInfo(UserRegistrationDto dto) {
        Borrower borrower = setFromDto(dto);
        borrower.setBirthday(dto.getBirthday());
        BorrowerAccount account = borrowerAccountRepo.save(new BorrowerAccount());
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
*/

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
        Set<Role> roles = new HashSet<>();
        borrower = new Borrower();

        if (investor != null) {
            borrower = new Borrower(investor);
        }

        borrower.setEmail(creds.getUsername());
        borrower.setActive(true); // add email activation for this
        borrower.setContactPersons(new ArrayList<>());
        borrower.setPassword(passwordEncoder.encode(creds.getPassword()));
        borrower.setLastVisit(LocalDateTime.now());
        roles.add(Role.REGISTERED_BORROWER);
        borrower.setRoles(roles);
        borrower.setPersonalFilled(false);
        borrower.setDocsFilled(false);
        borrower.setAddressFilled(false);
        borrower.setEmploymentFilled(false);
        borrower.setEducationFilled(false);
        borrower.setAssetsFilled(false);
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
        return borrower;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public Integer fillPersonalInfoAndSendSmsCode(Borrower borrower, BorrowerRegDto dto) {
        UserService.setUserFields(borrower, dto);
        borrower.setMaritalStatus(dto.getMaritalStatus());
        borrower.setKidsBefore18yo(dto.getKidsBefore18yo());
        borrower.setKidsAfter18yo(dto.getKidsAfter18yo());
        borrower.setInstagram(dto.getInstagram());
        borrower.setFacebook(dto.getFacebook());
        BorrowerAccount account = borrower.getBorrowerAccount() == null ? borrowerAccountRepo.save(new BorrowerAccount()) : borrower.getBorrowerAccount();
        account.setBorrower(borrower);
        account = borrowerAccountRepo.save(account);
        borrower.setBorrowerAccount(account);
        borrower.setPersonalFilled(true);
        checkIfRegistrationInfoFilled(borrower);
        borrowerRepo.save(borrower);
        return ConstantUtil.getRandom6DigitNumber();
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public Boolean savePassportAndItn(BorrowerPassportAndItnDto dto, Borrower borrower, MultipartFile file) {
        borrower.setPassport(passportService.createBorrowerPass(borrower, dto, file));
        borrower.setItn(dto.getItnNumber());
        borrower.setDocsFilled(true);
        checkIfRegistrationInfoFilled(borrower);
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public Boolean saveLivingAddress(Borrower borrower, AddressDto dto) {

        if (dto.isSameAddressInPassport() && dto.equals(getPassportAddress(borrower))) {
            borrower.setAddress(borrower.getPassport().getAddressOfRegistration());
        } else {
            borrower.setAddress(addressService.addressFromAddressDto(dto));
        }
        borrower.setHomeType(dto.getHomeType());
        borrower.setAddressFilled(true);
        checkIfRegistrationInfoFilled(borrower);
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public Boolean handleEmployment(Borrower borrower, EmploymentDto dto) {
        borrower.setEmployment( (borrower.getEmployment() != null) ?
                employmentService.update(borrower.getEmployment(), dto) :
                employmentService.create(dto) );
        List<ContactPerson> persons = borrower.getContactPersons();
        String[] names1 = dto.getContactPerson1Name().split(" ");
        String[] names2 = dto.getContactPerson2Name().split(" ");

        if (persons.isEmpty()) {
            persons.add(contactPersonRepo.save(new ContactPerson(names1[1], names1[0], names1[2], dto.getContactPerson1phone(), dto.getRelation1(), borrower)));
            persons.add(contactPersonRepo.save(new ContactPerson(names2[1], names2[0], names2[2], dto.getContactPerson2phone(), dto.getRelation2(), borrower)));
        } else {
            ContactPerson person1 = persons.get(0);
            person1.setFirstName(names1[1]);
            person1.setLastName(names1[0]);
            person1.setMiddleName(names1[2]);
            person1.setPhone(dto.getContactPerson1phone());
            person1.setRelationship(dto.getRelation1());
            contactPersonRepo.save(person1);
            ContactPerson person2 = persons.get(1);
            person2.setFirstName(names2[1]);
            person2.setLastName(names2[0]);
            person2.setMiddleName(names2[2]);
            person2.setPhone(dto.getContactPerson2phone());
            person2.setRelationship(dto.getRelation2());
            contactPersonRepo.save(person2);
        }

        borrower.setContactPersons(persons);
        borrower.setSocialStatus(socialStatusRepo.findByStatusUaLike(dto.getSocialStatus()));
        borrower.setMonthlyIncomeOfficial(dto.getMonthlyIncomeOfficial());
        borrower.setMonthlyIncomeAdditional(dto.getMonthlyIncomeAdditional());
        borrower.setAdditionalIncomeSource(dto.getAdditionalIncomeSource());
        borrower.setMonthlyExpenses(dto.getMonthlyExpenses());
        borrower.setScholarship(dto.getScholarship());
        borrower.setPension(dto.getPension());
        borrower.setEmployeesCount(dto.getEmployeesCount());
        borrower.setMonthlyObligations(dto.getMonthlyObligations());
        borrower.setMonthlyIncomeTotal(borrower.getMonthlyIncomeOfficial() + borrower.getMonthlyIncomeAdditional());
        borrower.setEmploymentFilled(true);
        checkIfRegistrationInfoFilled(borrower);
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public Boolean handleEducation(Borrower borrower, EducationDto dto) {
        borrower.setEducation(
                borrower.getEducation() != null ?
                        educationService.update(borrower.getEducation(), dto) :
                        educationService.create(dto));
        borrower.setEducationFilled(true);
        checkIfRegistrationInfoFilled(borrower);
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public Boolean handleAssets(Borrower borrower, AssetsDto dto) {
        borrower.setFlat(dto.getFlat());
        borrower.setHasHouse(dto.isHasHouse());
        borrower.setHasCar(dto.isHasCar());
        if (borrower.getHasCar()) {
            borrower.setCarAge(dto.getCarAge());
        }
        borrower.setWasAbroad(dto.isWasAbroad());
        borrower.setAssetsFilled(true);
        checkIfRegistrationInfoFilled(borrower);
        borrowerRepo.save(borrower);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
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

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public BorrowerPassportAndItnDto getDocsData(Borrower borrower) {
        Passport pass = borrower.getPassport();
        Address addr = pass.getAddressOfRegistration();
        return new BorrowerPassportAndItnDto(
                pass.isIdPassport(),
                pass.getIdPassNumber(),
                pass.getPaperPassSeries(),
                pass.getPaperPassNumber(),
                pass.getIssueDate(),
                pass.getIssuer(),
                borrower.getItn(),
                addr.getRegion(),
                addr.getDistrict(),
                addr.getPostalCode(),
                addr.getSettlement(),
                addr.getStreet(),
                addr.getNumber(),
                addr.getCorpsNo(),
                addr.getDoor(),
                pass.getPhotoWithPassportFileName().substring(pass.getPhotoWithPassportFileName().indexOf(".") + 1)
        );
    }

    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public AddressDto getAddrData(Borrower borrower) {
        Address addr = borrower.getAddress();
        return new AddressDto(
                addr.getPostalCode(),
                addr.getDoor(),
                addr.getCorpsNo(),
                addr.getNumber(),
                addr.getStreet(),
                addr.getSettlement(),
                addr.getDistrict(),
                addr.getRegion(),
                borrower.getHomeType(),
                borrower.getPassport() != null && borrower.getPassport().getAddressOfRegistration().equals(addr)
        );
    }

    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public AddressDto getPassportAddress(Borrower borrower) {
        Address addr = borrower.getPassport().getAddressOfRegistration();
        return new AddressDto(
                addr.getPostalCode(),
                addr.getDoor(),
                addr.getCorpsNo(),
                addr.getNumber(),
                addr.getStreet(),
                addr.getSettlement(),
                addr.getDistrict(),
                addr.getRegion(),
                "",
                false
        );
    }

    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public EmploymentDto getEmploymentData(Borrower borrower) {
        Employment empl = borrower.getEmployment();
        List<ContactPerson> contactPersons = borrower.getContactPersons();
        ContactPerson person1 = contactPersons.get(0);
        ContactPerson person2 = contactPersons.get(1);

        return new EmploymentDto(
                borrower.getSocialStatus().getStatusUa(),
                empl.getWorkSphere().getSphereUa(),
                empl.getLengthOfTotalEmploymentMo(),
                empl.getLengthOfCurrentEmploymentMo(),
                empl.getEmployerCount(),
                borrower.getMonthlyIncomeOfficial(),
                borrower.getMonthlyIncomeAdditional(),
                borrower.getAdditionalIncomeSource(),
                borrower.getScholarship(),
                borrower.getPension(),
                borrower.getEmployeesCount(),
                empl.getNextPaymentDate(),
                empl.getPaymentFrequency(),
                borrower.getMonthlyExpenses(),
                borrower.getMonthlyObligations(),
                person1.getPhone().substring(4),
                person2.getPhone().substring(4),
                person1.getLastName() + " " + person1.getFirstName() + " " + person1.getMiddleName(),
                person2.getLastName() + " " + person2.getFirstName() + " " + person2.getMiddleName(),
                person1.getRelationship(),
                person2.getRelationship());
    }

    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public EducationDto getEducationData(Borrower borrower) {
        Education education = borrower.getEducation();
        return new EducationDto(
                education.getEducationLevel(),
                education.getEducationField()
        );
    }


    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public AssetsDto getAssetsData(Borrower borrower) {
        return new AssetsDto(
                borrower.getFlat(),
                borrower.getHasHouse(),
                borrower.getHasCar(),
                borrower.getHasCar() ? borrower.getCarAge() : null,
                borrower.getWasAbroad() );
    }


    @PreAuthorize("hasAuthority('REGISTERED_BORROWER')")
    public RegSectionsFilledFlagsDto getSectionsFilledFlags(Borrower borrower) {
        return new RegSectionsFilledFlagsDto(
                borrower.getPersonalFilled(),
                borrower.getDocsFilled(),
                borrower.getAddressFilled(),
                borrower.getEmploymentFilled(),
                borrower.getEducationFilled(),
                borrower.getAssetsFilled());
    }

    private void checkIfRegistrationInfoFilled(Borrower borrower) {
        if ( borrower.getAssetsFilled() &&  borrower.getEducationFilled() && borrower.getEmploymentFilled()
                && borrower.getAddressFilled() && borrower.getDocsFilled() && borrower.getPersonalFilled()) {
            borrower.getRoles().add(Role.BORROWER_ON_CHECK);
        }
    }
}
