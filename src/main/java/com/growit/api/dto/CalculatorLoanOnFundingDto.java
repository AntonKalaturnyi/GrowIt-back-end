package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CalculatorLoanOnFundingDto extends AbstractDto {

    private long loanId;

    /** General loan data*/

    private int amount;

    private String term;

    private String amountToReturn;

    private String loanPurpose;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate applyDate;

    private double amountFunded;

    private int fulfillment;

    private String description;

    private String deadline;

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
}
