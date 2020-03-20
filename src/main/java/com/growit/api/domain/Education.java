package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "education")
@EqualsAndHashCode(callSuper = false)
public class Education extends AbstractEntity {

    private String educationLevel;

    private String educationField;

/*
    */
/* Regular school*//*

    private String school;

    private String specialization;


    */
/* University or so*//*

    private String collegeGraduated;
*/


}
