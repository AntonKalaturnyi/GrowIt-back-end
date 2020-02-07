package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "brwr_account")
@EqualsAndHashCode(callSuper = true)
public class BorrowerAccount extends Account {

    @OneToOne
    private Borrower borrower;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    @Override
    public String toString() {
        return "BorrowerAccount{" +
                "borrowerId=" + borrower.getId() +
                ", transactions=" + transactions +
                '}';
    }
}
