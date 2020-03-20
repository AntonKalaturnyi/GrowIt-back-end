package com.growit.api.service;

import com.growit.api.domain.Education;
import com.growit.api.dto.EducationDto;
import com.growit.api.repo.EducationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    private final EducationRepo educationRepo;

    @Autowired
    public EducationService(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }


    public Education create(EducationDto dto) {
        Education education = new Education();
        education.setEducationLevel(dto.getEducationLevel());
        education.setEducationField(dto.getEducationField());
        return educationRepo.save(education);
    }

}
