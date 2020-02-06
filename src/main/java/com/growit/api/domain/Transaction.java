package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = false)
public class Transaction extends AbstractEntity {

    private double previousBudget;

    private double transactionBudget;

    /** IN or OUT in ConstantUtil*/
    private String type;

    private String description;

    @ManyToOne
    private Account account;

}
