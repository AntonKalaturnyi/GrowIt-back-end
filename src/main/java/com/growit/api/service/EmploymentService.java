package com.growit.api.service;

import com.growit.api.domain.Employment;
import com.growit.api.dto.EmploymentDto;
import com.growit.api.repo.EmploymentRepo;
import com.growit.api.repo.WorkSphereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentService {

    private final EmploymentRepo employmentRepo;
    private final WorkSphereRepo workSphereRepo;

    @Autowired
    public EmploymentService(EmploymentRepo employmentRepo, WorkSphereRepo workSphereRepo) {
        this.employmentRepo = employmentRepo;
        this.workSphereRepo = workSphereRepo;
    }

    public Employment create(EmploymentDto dto) {
        Employment employment = new Employment();
        return update(employment, dto);
    }

    public Employment update(Employment employment, EmploymentDto dto) {
        employment.setLengthOfTotalEmploymentMo(dto.getLengthOfTotalEmploymentMo());
        employment.setLengthOfCurrentEmploymentMo(dto.getLengthOfCurrentEmploymentMo());
        employment.setTermOfUnemployment(dto.getTermOfUnemployment());
        employment.setEmployerCount(dto.getEmployerCount());
        employment.setWorkSphere(workSphereRepo.findBySphereUaLike(dto.getWorkSphere()));
        employment.setNextPaymentDate(dto.getNextPaymentDate());
        employment.setPaymentFrequency(dto.getPaymentFrequency());
        return employmentRepo.save(employment);

    }

}
