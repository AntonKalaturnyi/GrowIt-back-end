package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = false)
public class Transaction extends AbstractEntity {

    private String type;

    private double previousBalance;

    /** before commission*/
    private double amount;

    private double commission;

    /** actual amt, after commission*/
    private double resultAmount;

    private String description;

//    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    private Card card;

    @ManyToOne
    private Account account;

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", previousBalance=" + previousBalance +
                ", amount=" + amount +
                ", commission=" + commission +
                ", resultAmount=" + resultAmount +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
