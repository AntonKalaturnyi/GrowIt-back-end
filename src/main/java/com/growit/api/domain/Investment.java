package com.growit.api.domain;

import com.google.common.base.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "investments")
public class Investment extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToOne
    private Loan loan;

    private double amountInvested;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Investment)) return false;
        if (!super.equals(o)) return false;
        Investment that = (Investment) o;
        return Double.compare(that.getAmountInvested(), getAmountInvested()) == 0 &&
                Objects.equal(getLoan(), that.getLoan());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getLoan(), getAmountInvested());
    }
}
