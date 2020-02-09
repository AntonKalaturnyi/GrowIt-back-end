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

    /* ++ private LocalDateTime timeWhenSetted;
    * ++ LoanStatusController.create */

}

/*
    ON_FUNDING, ISSUED, FULLY_RETURNED, PARTIALLY_RETURNED, SOLD_TO_ANOTHER_INVESTOR,
    SOLD_TO_COLLECTOR, DELAYED, ON_GRACE_PERIOD, SIX_TO_14_DAYS_LATE, FIFTEEN_TO_31_DAYS_LATE,
    THIRTY_TWO_TO_120_DAYS_LATE, CHARGED_OFF
    */
