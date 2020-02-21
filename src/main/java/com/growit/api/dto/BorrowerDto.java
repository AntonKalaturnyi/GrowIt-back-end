package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerDto extends AbstractDto implements BorrowerTransferObject {

    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify borrower's id")
    private long borrowerId;

    private String workSphereString;

    private int monthlyIncomeOfficial;

    private int monthlyIncomeAdditional;

    private boolean married;

    private boolean divorced;

    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify how many kids before 18 years old do you have")
    private int kidsBefore18yo;

    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify how many kids after 18 years old do you have")
    private int kidsAfter18yo;

    // For test purposes
//    private long creditHistoryId;
//    private long creditCardId; - not in mapper
//    long[] contactPersonsIds;


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

    private String homeOwnershipString;

    private String jobTitle;

}
