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

    private String termOfUnemployment;

    private int employerCount;

    private int monthlyIncomeOfficial;

    private int monthlyIncomeAdditional;

    private String additionalIncomeSource;

    private int scholarship;

    private int pension;

    private int employeesCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime nextPaymentDate;

    private String paymentFrequency;

    private int monthlyExpenses;

    private int monthlyObligations;

    private String contactPerson1phone;
    private String contactPerson2phone;
    private String contactPerson1Name;
    private String contactPerson2Name;
    private String relation1;
    private String relation2;

    //    private String nameOfCompany;

//    private String EDRPOUcode;
/*
    private int numberOfEmployees;

    private String priority;

    private String bossFirstName;

    private String bossLastName;

    private String bossMiddleName;*/

    public EmploymentDto(String socialStatus,
                         String workSphere,
                         int lengthOfTotalEmploymentMo,
                         int lengthOfCurrentEmploymentMo,
                         int employerCount,
                         int monthlyIncomeOfficial,
                         int monthlyIncomeAdditional,
                         String additionalIncomeSource,
                         int scholarship,
                         int pension,
                         int employeesCount,
                         LocalDateTime nextPaymentDate,
                         String paymentFrequency,
                         int monthlyExpenses,
                         int monthlyObligations,
                         String contactPerson1phone,
                         String contactPerson2phone,
                         String contactPerson1Name,
                         String contactPerson2Name,
                         String relation1,
                         String relation2) {

        this.socialStatus = socialStatus;
        this.workSphere = workSphere;
        this.lengthOfTotalEmploymentMo = lengthOfTotalEmploymentMo;
        this.lengthOfCurrentEmploymentMo = lengthOfCurrentEmploymentMo;
        this.employerCount = employerCount;
        this.monthlyIncomeOfficial = monthlyIncomeOfficial;
        this.monthlyIncomeAdditional = monthlyIncomeAdditional;
        this.additionalIncomeSource = additionalIncomeSource;
        this.scholarship = scholarship;
        this.pension = pension;
        this.employeesCount = employeesCount;
        this.nextPaymentDate = nextPaymentDate;
        this.paymentFrequency = paymentFrequency;
        this.monthlyExpenses = monthlyExpenses;
        this.monthlyObligations = monthlyObligations;

        this.contactPerson1phone = contactPerson1phone;
        this.contactPerson2phone = contactPerson2phone;
        this.contactPerson1Name = contactPerson1Name;
        this.contactPerson2Name = contactPerson2Name;
        this.relation1 = relation1;
        this.relation2 = relation2;


    }
}
