package com.growit.api.service;

import com.growit.api.domain.Employment;
import com.growit.api.dto.EmploymentDto;
import com.growit.api.repo.EmploymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentService {

    private final EmploymentRepo employmentRepo;

    @Autowired
    public EmploymentService(EmploymentRepo employmentRepo) {
        this.employmentRepo = employmentRepo;
    }

    public Employment create(EmploymentDto dto) {
        Employment employment = new Employment();
        employment.setLengthOfTotalEmploymentMo(dto.getLengthOfTotalEmploymentMo());
        employment.setLengthOfCurrentEmploymentMo(dto.getLengthOfCurrentEmploymentMo());
        employment.setEmployerCount(dto.getEmployerCount());
        employment.setNextPaymentDate(dto.getNextPaymentDate());
        employment.setPaymentFrequency(dto.getPaymentFrequency());
        return employmentRepo.save(employment);
    }

}
