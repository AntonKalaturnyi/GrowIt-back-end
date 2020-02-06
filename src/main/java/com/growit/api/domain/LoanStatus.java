package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "loan_status")
@EqualsAndHashCode(callSuper = false)
public class LoanStatus extends AbstractEntity {

    private String statusUa;

    private String statusEng;

}

/*
    ON_FUNDING, ACTIVE, FULLY_RETURNED, PARTIALLY_RETURNED, SOLD_TO_ANOTHER_INVESTOR,
    SOLD_TO_COLLECTOR, DELAYED, ON_GRACE_PERIOD, SIX_TO_15_DAYS_LATE, SIXTEEN_TO_30_DAYS_LATE,
    THIRTY_ONE_TO_120_DAYS_LATE, CHARGED_OFF
    */
