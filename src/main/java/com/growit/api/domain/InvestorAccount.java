package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "inv_account")
@EqualsAndHashCode(callSuper = false)
public class InvestorAccount extends Account {

    @OneToOne
    private Investor investor;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    private double expectedProfitability;

    private int currentActiveLoans;

    private int gracePeriodLoans;

    private int late6To14Days;

    private int late15To31Days;

    private int late32To120DaysLoans;

    private int chargedOffLoans;

    private double investedFunds;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<MonthlyStatement> monthlyStatements;

    @Override
    public String toString() {
        return "InvestorAccount{" +
                "investorId=" + investor.getId() +
                ", transactions=" + transactions +
                ", expectedProfitability=" + expectedProfitability +
                ", currentActiveLoans=" + currentActiveLoans +
                ", gracePeriodLoans=" + gracePeriodLoans +
                ", late6To15Days=" + late6To14Days +
                ", late16To30Days=" + late15To31Days +
                ", late31To120DaysLoans=" + late32To120DaysLoans +
                ", chargedOffLoans=" + chargedOffLoans +
                ", investedFunds=" + investedFunds +
                ", monthlyStatements=" + monthlyStatements +
                '}';
    }
}
