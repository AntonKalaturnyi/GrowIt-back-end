package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoanDto extends AbstractDto {

    private int amountRequested;

    private int amountApproved;

    private int amountToReturn;

    private int period;

    private double monthlyPayment;

    private double amountFunded;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateReturnDue;

    /* ((amountApproved/amountFunded)*100) */
    private double percentFunded;

    /* (monthlyPayment/salary) * 100%  */
    private double dtiRatio;

    private String loanPurpose;

    //verification
    private String description;

    private String loanStatus;

    private long borrowerId;

    /* APR, convert to monthly in service if needed  */
    private double profitability;

}
