package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CabinetInvestmentDto {


    private long investmentId; // Y

    /** General loan data*/
    private int amount; // Y
    private double investedAmount; // Y
    private String term; // Y
    private String interestRate; // Y
    private String plannedReturn; //
    private String loanPurpose; // Y
    private double amountFunded; // Y
    private int fulfillment; // Y
    private String description; // Y
    private String deadline; // Y
    private String dateInvested; // Y



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
