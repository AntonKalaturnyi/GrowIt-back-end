package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerDto extends AbstractDto {

    private long borrowerId;

    private String workSphereString;

    private int monthlyIncomeOfficial;

    private int monthlyIncomeAdditional;

//---    private Set<Employer> employers = new HashSet<>();
//    long[] employersIds;

    @NotNull(groups = {New.class, Existing.class},
            message = "Please provide your marital status")
    private boolean married;

    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify how many kids before 18 years old do you have")
    private int kidsBefore18yo;

    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify how many kids after 18 years old do you have")
    private int kidsAfter18yo;

    private long creditHistoryId;

    private String homePhone;

    private String workPhone;

    private String fax;

    private String instagram;

    private String facebook;

    /** On front-end: Lang: UA, ENG
     Allowed types: employed; self-employed; entrepreneur*/
    private String workType;

    private String spouseITN;

    /** AFS (Anti Fraud System) UBKI */

    /* In case 'workType'is 'entrepreneur' */
    private String EDRPOUcode;

//    long[] contactPersonsIds;

    private String homeOwnershipString;

//    private long borrowerAccountId;

    private String jobTitle;

//    private long creditCardId;

}
