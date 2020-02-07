package com.growit.api.service;

import com.growit.api.domain.ContactPerson;
import com.growit.api.dto.ContactPersonDto;
import com.growit.api.mapper.ContactPersonMapper;
import com.growit.api.repo.ContactPersonRepo;
import com.growit.api.repo.ItnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactPersonService {

    private final ContactPersonRepo contactPersonRepo;
    private final ItnRepo itnRepo;
    private final ContactPersonMapper mapper;

    @Autowired
    public ContactPersonService(ContactPersonRepo contactPersonRepo, ItnRepo itnRepo, ContactPersonMapper mapper) {
        this.contactPersonRepo = contactPersonRepo;
        this.itnRepo = itnRepo;
        this.mapper = mapper;
    }

    public ContactPersonDto create(ContactPersonDto dto) {
        ContactPerson person = mapper.toEntity(dto);
        person.setItn(itnRepo.findById(dto.getItnId()).get());
        return mapper.toDto(contactPersonRepo.save(person));
    }

}
