package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestorAccountDto extends AbstractDto {

    private double availableBalance;

    private double investedFunds;

    /*    private long investorId;

    private double expectedProfitability;

    private int currentActiveLoans;

    private int gracePeriodLoans;

    private int late6To14Days;

    private int late15To31Days;

    private int late32To120DaysLoans;

    private int chargedOffLoans;

    private double currentBalanceAvailable;*/

    public InvestorAccountDto() {
        this.availableBalance = 0;
        this.investedFunds = 0;
    }
}
