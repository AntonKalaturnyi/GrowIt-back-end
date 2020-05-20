package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "card")
@EqualsAndHashCode(callSuper = false)
public class Card extends AbstractEntity {

    private String number;

    private String expMonth;

    private String expYear;

    private String CVV;

    private boolean verified;

}
