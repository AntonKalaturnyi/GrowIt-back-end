package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "social_status")
@EqualsAndHashCode(callSuper = false)
public class SocialStatus extends AbstractEntity {

    private String statusEng;

    private String statusUa;


}
