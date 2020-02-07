package com.growit.api.service;

import com.growit.api.domain.Address;
import com.growit.api.dto.AddressDto;
import com.growit.api.dto.LoanDto;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    void testAddress() {
        Address address = new Address();
        LoanDto dto = new LoanDto();
    }

    public AddressDto create(AddressDto dto) {
        return null;
    }
}
