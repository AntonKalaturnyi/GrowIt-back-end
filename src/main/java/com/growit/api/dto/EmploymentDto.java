package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmploymentDto extends AbstractDto {

    private String socialStatus;

    private String workSphere;

    private int lengthOfTotalEmploymentMo;  // In months

    private int lengthOfCurrentEmploymentMo;  // In months

    private int employerCount;

    private int monthlyIncomeOfficial;

    private int monthlyIncomeAdditional;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime nextPaymentDate;

    private String paymentFrequency;

    private int monthlyExpenses;

    private int monthlyObligations;


    //    private String nameOfCompany;

//    private String EDRPOUcode;
/*
    private int numberOfEmployees;

    private String priority;

    private String bossFirstName;

    private String bossLastName;

    private String bossMiddleName;*/
}
