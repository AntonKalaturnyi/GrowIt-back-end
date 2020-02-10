package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "rates")
@EqualsAndHashCode(callSuper = false)
public class Rates extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private SafetyClass safetyClass;

    private double dailyRateForBorrower;

    private double monthlyRateForBorrower;

    private double yearlyRateForBorrower;

    private double riskPercentMin;

    private double riskPercentMax;

    private int firstLoanAmountLimit;

    private int secondLoanAmountLimit;

    private int requiredCollateralFromAmount;

    private int minCollateralCoveragePercent;

}
