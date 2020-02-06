package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "employer")
@EqualsAndHashCode(callSuper = false)
public class Employer extends AbstractEntity {

    private String nameOfCompany;

    private String EDRPOUcode;

    @ManyToOne
    @JoinColumn(name = "work_sphere_id")
    private WorkSphere workSphere;

    @Size(max = 50)
    @Column(name = "boss_first_name", length = 50)
    private String bossFirstName;

    @Size(max = 50)
    @Column(name = "boss_last_name", length = 50)
    private String bossLastName;

    @Size(max = 50)
    @Column(name = "boss_middle_name", length = 50)
    private String bossMiddleName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address jobAddress;

    private int lengthOfCurrentEmployment;  // In months

    private int lengthOfTotalEmployment;  // In months

    private int numberOfEmployees;

    private String priority;

}
