package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DashboardLoanDto extends AbstractDto {

    private String rank;

    private int score;

    private int amount;

    private String term;

    private String profitability;

    private String loanPurpose;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate applyDate;

    private double amountFunded;

    private String description;

    private String timeLeft;

    public DashboardLoanDto(String rank,
                            Integer score,
                            int amount,
                            String term,
                            String profitability,
                            String loanPurpose,
                            LocalDate applyDate,
                            double amountFunded,
                            String description, String timeLeft) {
        this.rank = rank;
        this.score = score;
        this.amount = amount;
        this.term = term;
        this.profitability = profitability;
        this.loanPurpose = loanPurpose;
        this.applyDate = applyDate;
        this.amountFunded = amountFunded;
        this.description = description;
        this.timeLeft = timeLeft;
    }
}
