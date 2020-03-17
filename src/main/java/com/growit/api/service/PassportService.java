package com.growit.api.service;

import com.growit.api.domain.Passport;
import com.growit.api.dto.BorrowerPassportAndItnDto;
import com.growit.api.dto.InvestorPassportAndItnDto;
import com.growit.api.repo.AddressRepo;
import com.growit.api.repo.BorrowerRepo;
import com.growit.api.repo.PassportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PassportService {

    @Value("${upload.path}")
    private String uploadPath;

    private final BorrowerRepo borrowerRepo;
    private final PassportRepo passportRepo;
    private final AddressService addressService;

    @Autowired
    public PassportService(BorrowerRepo borrowerRepo, PassportRepo passportRepo, AddressService addressService) {
        this.borrowerRepo = borrowerRepo;
        this.passportRepo = passportRepo;
        this.addressService = addressService;
    }
/*

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Passport createBorrowerPass(BorrowerPassportDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        Passport passport = new Passport();
        Address addressOfRegistration = new Address();
        return setFields(passport, borrower, addressOfRegistration, dto);
    }

*/


    @Transactional
    public Passport createInvestorPass(InvestorPassportAndItnDto dto) {
        Passport passport = fillGeneralData(dto);
        return passportRepo.save(passport);
    }

    @Transactional
    public Passport createBorrowerPass(BorrowerPassportAndItnDto dto, MultipartFile file) {
        Passport passport = fillGeneralData(dto);
//        passport.setPhotoWithPassport(dto.getP);
        passport.setAddressOfRegistration(addressService.addressFromDto(dto));
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            passport.setPhotoWithPassportFileName(resultFilename);
        } else System.out.println(" &&& OriginalFilename is null &&&");
        return passportRepo.save(passport);
    }

/*    @PreAuthorize("hasAuthority('BORROWER')")
    public Passport updateBorrowerPass(BorrowerPassportDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        Passport passport = passportRepo.findById(dto.getPassportId()).get();
        Address addressOfRegistration = addressRepo.findById(dto.getAddressId()).get();
        return setFields(passport, borrower, addressOfRegistration, dto);
    }*/

/*
    private Passport setFields(Passport passport, Borrower borrower, Address addressOfRegistration, BorrowerPassportDto dto) {

        if (dto.isIdPassport()) {
            if (dto.isHasIdPassPhotoOrScan()) {
                passport.setIdPassPhotoOrScan(dto.getIdPassPhotoOrScan());
            }
            passport.setIdPassNumber(dto.getIdPassNumber());
        } else {
            passport.setPaperPassScanOrPhoto(dto.getPaperPassScanOrPhoto());
            passport.setPaperPassSeries(dto.getPaperPassSeries());
            passport.setPaperPassNumber(dto.getPaperPassNumber());
        }
        passport.setIdPassport(dto.isIdPassport());
        passport.setHasIdPassPhotoOrScan(dto.isHasIdPassPhotoOrScan());
        passport.setPhotoWithPassport(dto.getPhotoWithPassport());
        addressOfRegistration.setCountry(dto.getCountry());
        addressOfRegistration.setRegion(dto.getRegion());
        addressOfRegistration.setDistrict(dto.getDistrict());
        addressOfRegistration.setSettlement(dto.getSettlement());
        addressOfRegistration.setStreet(dto.getStreet());
        addressOfRegistration.setNumber(dto.getNumber());
        addressOfRegistration.setDoor(dto.getDoor());
        addressOfRegistration.setPostalCode(dto.getPostalCode());
//        passport.setAddressOfRegistration(addressRepo.save(addressOfRegistration));
        borrower.setPassport(passportRepo.save(passport));
        borrowerRepo.save(borrower);
        return passport;
    }
*/



    private Passport fillGeneralData(InvestorPassportAndItnDto dto) {
        Passport passport = new Passport();
        passport.setIdPassport(dto.isIdPassport());
        if (passport.isIdPassport()) {
            passport.setIdPassNumber(dto.getIdPassNumber());
        } else {
            passport.setPaperPassSeries(dto.getPaperPassSeries());
            passport.setPaperPassNumber(dto.getPaperPassNumber());
        }
        passport.setIssuer(dto.getIssuer());
        passport.setIssueDate(dto.getIssueDate());
        return passport;
    }
}
