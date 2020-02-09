package com.growit.api.dto;

import com.growit.api.domain.Investment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestmentDto extends AbstractDto {

    private long investorId;

    private long loanId;

    private double amountInvested;

    public InvestmentDto(Investment inv) {
        this.id = inv.getId();
        this.investorId = inv.getInvestor().getId();
        this.loanId = inv.getLoan().getId();
        this.amountInvested = inv.getAmountInvested();
        this.created = inv.getCreated();
        this.updated = inv.getUpdated();
    }
}
