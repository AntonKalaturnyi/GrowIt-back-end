package com.growit.api.service;

import com.growit.api.domain.Address;
import com.growit.api.domain.Borrower;
import com.growit.api.domain.Passport;
import com.growit.api.dto.BorrowerPassportDto;
import com.growit.api.dto.InvestorPassportDto;
import com.growit.api.repo.AddressRepo;
import com.growit.api.repo.BorrowerRepo;
import com.growit.api.repo.PassportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassportService {

    private final BorrowerRepo borrowerRepo;
    private final PassportRepo passportRepo;
    private final AddressRepo addressRepo;

    @Autowired
    public PassportService(BorrowerRepo borrowerRepo, PassportRepo passportRepo, AddressRepo addressRepo) {
        this.borrowerRepo = borrowerRepo;
        this.passportRepo = passportRepo;
        this.addressRepo = addressRepo;
    }

    @Transactional
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public Passport createBorrowerPass(BorrowerPassportDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        Passport passport = new Passport();
        Address addressOfRegistration = new Address();
        return setFields(passport, borrower, addressOfRegistration, dto);
    }

    @Transactional
    public InvestorPassportDto createInvestorPass(InvestorPassportDto card) {
        return null;
    }

    @PreAuthorize("hasAuthority('BORROWER')")
    public Passport updateBorrowerPass(BorrowerPassportDto dto) {
        Borrower borrower = borrowerRepo.findById(dto.getBorrowerId()).get();
        Passport passport = passportRepo.findById(dto.getPassportId()).get();
        Address addressOfRegistration = addressRepo.findById(dto.getAddressId()).get();
        return setFields(passport, borrower, addressOfRegistration, dto);
    }

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
        passport.setAddressOfRegistration(addressRepo.save(addressOfRegistration));
        borrower.setPassport(passportRepo.save(passport));
        borrowerRepo.save(borrower);
        return passport;
    }
}
