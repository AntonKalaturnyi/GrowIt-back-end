package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SafetyClass extends AbstractEntity {

    private String safetyClass;

    private double defaultProbabilityUbki;

    private double rate;
}
