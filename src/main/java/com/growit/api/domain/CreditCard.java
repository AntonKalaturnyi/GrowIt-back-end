package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "credit_card")
@EqualsAndHashCode(callSuper = false)
public class CreditCard extends AbstractEntity {

    private String number;

    private String expMonth;

    private String expYear;

    private String CVV;

    private boolean verified;

}
