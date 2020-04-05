package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;
import lombok.Data;
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
public class Loan extends AbstractEntity {

    @Column(nullable = true)
    private Integer amountRequested;

    @Column(nullable = true)
    private Integer amountApproved;  // +

    @Column(nullable = true)
    private Integer amountToReturn;  // +

    private String term; // "12d" - 30 days "12m" - 12 month

    @Column(nullable = true)
    private Integer period; // days

    private double monthlyPayment;  // + = amountToReturn if term <= 1 month

    private double amountFunded;

    @Column(name = "date_return_due", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateReturnDue;

    @Column(name = "date_released", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateReleasedOnDashboard;

    /* ((amountFunded/Amount)*100) */
    private Double percentFunded;

    /* (monthlyObligations + monthlyPayment/salary) * 100%  */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        if (!super.equals(o)) return false;
        Loan loan = (Loan) o;
        return Double.compare(loan.getMonthlyPayment(), getMonthlyPayment()) == 0 &&
                Double.compare(loan.getAmountFunded(), getAmountFunded()) == 0 &&
                Objects.equal(getAmountRequested(), loan.getAmountRequested()) &&
                Objects.equal(getAmountApproved(), loan.getAmountApproved()) &&
                Objects.equal(getAmountToReturn(), loan.getAmountToReturn()) &&
                Objects.equal(getTerm(), loan.getTerm()) &&
                Objects.equal(getPeriod(), loan.getPeriod()) &&
                Objects.equal(getDateReturnDue(), loan.getDateReturnDue()) &&
                Objects.equal(getDateReleasedOnDashboard(), loan.getDateReleasedOnDashboard()) &&
                Objects.equal(getPercentFunded(), loan.getPercentFunded()) &&
                Objects.equal(getDtiRatio(), loan.getDtiRatio()) &&
                Objects.equal(getSafetyRank(), loan.getSafetyRank()) &&
                Objects.equal(getVerificationScore(), loan.getVerificationScore()) &&
                Objects.equal(getLoanPurpose(), loan.getLoanPurpose()) &&
                Objects.equal(getDescription(), loan.getDescription()) &&
                Objects.equal(getStatus(), loan.getStatus()) &&
                Objects.equal(getProfitability(), loan.getProfitability());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getAmountRequested(),
                getAmountApproved(), getAmountToReturn(), getTerm(),
                getPeriod(), getMonthlyPayment(), getAmountFunded(), getDateReturnDue(),
                getDateReleasedOnDashboard(), getPercentFunded(), getDtiRatio(), getSafetyRank(),
                getVerificationScore(), getLoanPurpose(), getDescription(), getStatus(), getProfitability());
    }
}
