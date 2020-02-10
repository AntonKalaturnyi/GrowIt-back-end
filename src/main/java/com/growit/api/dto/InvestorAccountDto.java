package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestorAccountDto extends AbstractDto {

    private double availableBalance;

    private String currency;

    private long investorId;

    private double expectedProfitability;

    private int currentActiveLoans;

    private int gracePeriodLoans;

    private int late6To14Days;

    private int late15To31Days;

    private int late32To120DaysLoans;

    private int chargedOffLoans;

    private double currentBalanceAvailable;

    private double investedFunds;

}
