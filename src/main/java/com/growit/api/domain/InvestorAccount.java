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

    private int late6To15Days;

    private int late16To30Days;

    private int late31To120DaysLoans;

    private int chargedOffLoans;

    private double currentBalanceAvailable;

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
                ", late6To15Days=" + late6To15Days +
                ", late16To30Days=" + late16To30Days +
                ", late31To120DaysLoans=" + late31To120DaysLoans +
                ", chargedOffLoans=" + chargedOffLoans +
                ", currentBalanceAvailable=" + currentBalanceAvailable +
                ", investedFunds=" + investedFunds +
                ", monthlyStatements=" + monthlyStatements +
                '}';
    }
}
