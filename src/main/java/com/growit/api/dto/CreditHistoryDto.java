package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditHistoryDto extends AbstractDto {

    private Integer currentOpenCredits;

    private Integer currentDebtAmount;

    private boolean hasDelayInCurrentPeriod;

    private Integer currentOverdueDebtAmount;

    private Integer currentDelayInDays;

    private Integer payedOffInOtherOrgs;

    private Integer plannedObligatoryPaymentInCurrentPeriod;

}
