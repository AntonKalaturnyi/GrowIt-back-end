package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditHistoryDto extends AbstractDto {

    private LocalDateTime earliestCreditLineDate;

    private int totalCredits;

    private int payedOffCredits;

    private int delayedCredits;

    private int defaultedCredits;

    private int GrowitPayedOffCredits;

    private int GrowitDelayedCredits;

    private int GrowitDefaultedCredits;

    private int revolvingCreditBalance;

    private int revolvingLineUtilization; //(%)

    private int inquiriesInLast6Mo;

    private int accountsNoDelinqued;

    private int delinquedAmount;

    private int DelinquenciesInLast2Ys;

    private int MoSinceLastDelinq;

//    private Set<PublicRecord> publicRecords = new HashSet<>();

    private int moSinceLastPublicRecord;

    private int moSinceLastDerogatory;

}
