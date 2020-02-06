package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "home_ownership")
@EqualsAndHashCode(callSuper = false)
public class HomeOwnership extends AbstractEntity {

    private String homeOwnershipEng;

    private String homeOwnershipUa;

}
