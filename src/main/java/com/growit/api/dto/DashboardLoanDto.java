package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DashboardLoanDto extends AbstractDto {

    private long loanId;

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

    private int currentOpenCredits; // DTO
    private int currentDebtAmount;  // Сумма тек. задолженности
    private boolean hasDelayInCurrentPeriod; // Признак наличия просрочки в тек.периоде
    private int currentOverdueDebtAmount;  // Сумма тек. просроченной задолженности
    private int currentDelayInDays; // Текущее кол-во дней просрочки
    private int payedOffInOtherOrgs; // DTO
    private int payedInGrowit;  // DTO


    public DashboardLoanDto(long loanId, String rank, int score, int amount,
                            String term, String profitability, String loanPurpose,
                            LocalDate applyDate, double amountFunded, int fulfillment,
                            String description, String timeLeft, LocalDate registrationDate,
                            String maritalStatus, String kidsBefore18yo, String kidsAfter18yo,
                            String placeOfLiving, int age, String socialStatus, int monthlyIncome,
                            int monthlyExpenses, int pti, int currentOpenCredits, int currentDebtAmount,
                            boolean hasDelayInCurrentPeriod, int currentOverdueDebtAmount,
                            int currentDelayInDays, int payedOffInOtherOrgs, int payedInGrowit) {
        this.loanId = loanId;
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
        this.registrationDate = registrationDate;
        this.maritalStatus = maritalStatus;
        this.kidsBefore18yo = kidsBefore18yo;
        this.kidsAfter18yo = kidsAfter18yo;
        this.placeOfLiving = placeOfLiving;
        this.age = age;
        this.socialStatus = socialStatus;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
        this.pti = pti;
        this.currentOpenCredits = currentOpenCredits;
        this.currentDebtAmount = currentDebtAmount;
        this.hasDelayInCurrentPeriod = hasDelayInCurrentPeriod;
        this.currentOverdueDebtAmount = currentOverdueDebtAmount;
        this.currentDelayInDays = currentDelayInDays;
        this.payedOffInOtherOrgs = payedOffInOtherOrgs;
        this.payedInGrowit = payedInGrowit;
    }
}
