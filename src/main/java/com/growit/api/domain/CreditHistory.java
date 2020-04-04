package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "credit_history")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditHistory extends AbstractEntity {


    private Integer currentOpenCredits; // DTO

    private Integer currentDebtAmount;  // Сумма тек. задолженности

    private boolean hasDelayInCurrentPeriod; // Признак наличия просрочки в тек.периоде

    private Integer currentOverdueDebtAmount;  // Сумма тек. просроченной задолженности

    private Integer currentDelayInDays; // Текущее кол-во дней просрочки

    private Integer payedOffInOtherOrgs; // DTO

    private Integer payedInGrowit;  // DTO

    private Integer plannedObligatoryPaymentInCurrentPeriod; // Сумма планового обяз. платежа в тек.периоде, врах в PTI

    @ManyToMany
    @JoinTable(name = "joint_record_chistory",
            joinColumns = { @JoinColumn(name = "credit_history_id") },
            inverseJoinColumns = { @JoinColumn(name = "public_record_id")} )
    private Set<PublicRecord> publicRecords = new HashSet<>();


    //    private String dealStatus;

//    private int totalCredits;
//
//    private int delayedCredits;
//
//    private int defaultedCredits;

    //    private LocalDateTime earliestCreditLineDate;

//    private int revolvingCreditBalance;

//    private int revolvingLineUtilization; //(%)

//    private int inquiriesInLast6Mo;

//    private int accountsNoDelinqued;

//    private int delinquedAmount;

//    private int DelinquenciesInLast2Ys;

//    private int MoSinceLastDelinq;


//    private int moSinceLastPublicRecord;

//    private int moSinceLastDerogatory;

    //private int collectExclosuresMedical; // ?
}
