package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loans")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Loan extends AbstractEntity {

    private Integer amountRequested;

    private Integer amountApproved;  // +

    private Integer amountToReturn;  // +

    private String term; // "12d" - 30 days "12m" - 12 month

    @Column(nullable = true)
    private Integer period; // days

    private double monthlyPayment;  // +

    private double amountFunded;

    @Column(name = "date_return_due", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateReturnDue;

    /* ((amountFunded/Amount)*100) */
    private Double percentFunded;

    /* (monthlyPayment/salary) * 100%  */
    @Column(nullable = true)
    private Double dtiRatio;  // +

    private String safetyRank;

    @Column(nullable = true)
    private Integer verificationScore;

    @ManyToOne
    private LoanPurpose loanPurpose;

    //verification
    @Column(length = 600)
    private String description;

    @ManyToOne
    private LoanStatus status;

    @ManyToOne
    private Borrower borrower;

    /* APR, convert to monthly in service if needed  */
    private Double profitability;

}
