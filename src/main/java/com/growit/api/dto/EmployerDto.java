package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployerDto extends AbstractDto {

    private String nameOfCompany;

    private String EDRPOUcode;

    private long workSphereId;

    private String bossFirstName;

    private String bossLastName;

    private String bossMiddleName;

    private long jobAddressId;

    private int lengthOfCurrentEmployment;  // In months

    private int lengthOfTotalEmployment;  // In months

    private int numberOfEmployees;

    private String priority;
}
