package com.growit.api.service;

import com.growit.api.domain.Address;
import com.growit.api.dto.AddressDto;
import com.growit.api.dto.BorrowerPassportAndItnDto;
import com.growit.api.dto.LoanDto;
import com.growit.api.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public Address addressFromDto(BorrowerPassportAndItnDto dto) {
        return addressRepo.save(new Address(dto.getRegion(), dto.getDistrict(), dto.getPostalCode(), dto.getSettlement(), dto.getStreet(),
                dto.getNumber(), dto.getCorpsNo(), dto.getDoor()));
    }

    public Address updateAddressFromDto(Address addr, BorrowerPassportAndItnDto dto) {
        addr.setRegion(dto.getRegion());
        addr.setDistrict(dto.getDistrict());
        addr.setPostalCode(dto.getPostalCode());
        addr.setSettlement(dto.getSettlement());
        addr.setStreet(dto.getStreet());
        addr.setNumber(dto.getNumber());
        addr.setCorpsNo(dto.getCorpsNo());
        addr.setDoor(dto.getDoor());
        return addressRepo.save(addr);
    }

    public Address addressFromAddressDto(AddressDto dto) {
        return addressRepo.save(new Address(dto.getRegion(), dto.getDistrict(), dto.getPostalCode(), dto.getSettlement(), dto.getStreet(),
                dto.getNumber(), dto.getCorpsNo(), dto.getDoor()));
    }

    void testAddress() {
        Address address = new Address();
        LoanDto dto = new LoanDto();
    }

    public AddressDto create(AddressDto dto) {
        return null;
    }
}
