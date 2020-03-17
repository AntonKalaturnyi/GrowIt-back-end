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

    void testAddress() {
        Address address = new Address();
        LoanDto dto = new LoanDto();
    }

    public AddressDto create(AddressDto dto) {
        return null;
    }
}
