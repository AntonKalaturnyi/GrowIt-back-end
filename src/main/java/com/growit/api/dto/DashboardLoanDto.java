package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DashboardLoanDto extends AbstractDto {

    /** General loan data*/

    private String rank;

    private int score;

    private int amount;

    private String term;

    private String profitability;

    private String loanPurpose;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate applyDate;

    private double amountFunded;

    private int fulfillment;

    private String description;

    private String timeLeft;

    /*** Expandable details*/

    /** Borrower data*/

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate registrationDate;
    private String maritalStatus;
    private String kidsBefore18yo;
    private String kidsAfter18yo;
    private String placeOfLiving;


    /** Financial data*/

    private int age;
    private String socialStatus;
    private int monthlyIncome;
    private int monthlyExpenses;
    private int pti;

    /** Credit history*/









    public DashboardLoanDto(String rank,
                            Integer score,
                            int amount,
                            String term,
                            String profitability,
                            String loanPurpose,
                            LocalDate applyDate,
                            double amountFunded,
                            int fulfillment,
                            String description, String timeLeft) {
        this.rank = rank;
        this.score = score;
        this.amount = amount;
        this.term = term;
        this.profitability = profitability;
        this.loanPurpose = loanPurpose;
        this.applyDate = applyDate;
        this.amountFunded = amountFunded;
        this.fulfillment = fulfillment;
        this.description = description;
        this.timeLeft = timeLeft;
    }
}
