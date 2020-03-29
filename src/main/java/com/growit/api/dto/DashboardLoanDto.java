package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DashboardLoanDto extends AbstractDto {

    private String rank;

    private Integer score;

    private int amount;

    private String term;

    private String profitability;

    private String loanPurpose;

    private LocalDateTime applyDate;

    private double amountFunded;

    private String description;

    public DashboardLoanDto(String rank,
                            Integer score,
                            int amount,
                            String term,
                            String profitability,
                            String loanPurpose,
                            LocalDateTime applyDate,
                            double amountFunded,
                            String description) {
        this.rank = rank;
        this.score = score;
        this.amount = amount;
        this.term = term;
        this.profitability = profitability;
        this.loanPurpose = loanPurpose;
        this.applyDate = applyDate;
        this.amountFunded = amountFunded;
        this.description = description;
    }
}
