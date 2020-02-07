package com.growit.api.service;

import com.growit.api.dto.EmployerDto;
import com.growit.api.mapper.EmployerMapper;
import com.growit.api.repo.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {

    private final EmployerRepo employerRepo;
    private final EmployerMapper mapper;

    @Autowired
    public EmployerService(EmployerRepo employerRepo, EmployerMapper mapper) {
        this.employerRepo = employerRepo;
        this.mapper = mapper;
    }

    public EmployerDto create(EmployerDto dto) {
        return mapper.toDto(employerRepo.save(mapper.toEntity(dto)));
    }

}
