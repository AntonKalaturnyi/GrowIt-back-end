package com.growit.api.dto;

import com.growit.api.domain.Borrower;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerUpdateDto extends UserRegistrationDto implements BorrowerTransferObject {

    private String workSphereString;

    private int monthlyIncomeOfficial;

    private int monthlyIncomeAdditional;

    private boolean married;


    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify how many kids before 18 years old do you have")
    private int kidsBefore18yo;

    @NotNull(groups = {New.class, Existing.class},
            message = "Please specify how many kids after 18 years old do you have")
    private int kidsAfter18yo;

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

    public BorrowerUpdateDto(Borrower bwr) {
        super(bwr);
        this.workSphereString = bwr.getWorkSphere().getSphereEng();
        this.monthlyIncomeOfficial = bwr.getMonthlyIncomeOfficial();
        this.monthlyIncomeAdditional = bwr.getMonthlyIncomeAdditional();
        this.married = bwr.isMarried();
        this.kidsBefore18yo = bwr.getKidsBefore18yo();
        this.kidsAfter18yo = bwr.getKidsAfter18yo();
        this.instagram = bwr.getInstagram();
        this.facebook = bwr.getFacebook();
        this.workType = bwr.getWorkType();
        this.EDRPOUcode = bwr.getEDRPOUcode();
        this.homeOwnershipString = bwr.getHomeOwnership().getHomeOwnershipEng();
        this.jobTitle = bwr.getJobTitle();
    }
}
