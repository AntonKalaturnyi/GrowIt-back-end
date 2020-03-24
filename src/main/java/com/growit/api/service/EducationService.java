package com.growit.api.service;

import com.growit.api.domain.Education;
import com.growit.api.dto.EducationDto;
import com.growit.api.repo.EducationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EducationService {

    private final EducationRepo educationRepo;

    @Autowired
    public EducationService(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    public Education create(EducationDto dto) {
        Education education = new Education();
        return update(education, dto);
    }

    @Transactional
    public Education update(Education education, EducationDto dto) {
        education.setEducationLevel(dto.getEducationLevel());
        education.setEducationField(dto.getEducationField());
        return educationRepo.save(education);
    }
}
