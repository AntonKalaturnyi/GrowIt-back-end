package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EducationDto extends AbstractDto {

    private String educationLevel;

    private String educationField;

    public EducationDto(String educationLevel, String educationField) {
        this.educationLevel = educationLevel;
        this.educationField = educationField;
    }
}
