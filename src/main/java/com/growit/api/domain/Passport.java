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
@Table(name = "passport")
@EqualsAndHashCode(callSuper = false)
public class Passport extends AbstractEntity {

    private boolean idPassport;

    private boolean hasIdPassScan;

    private String idPassScanFile;

    private String idPassNumber;

    private boolean hasPaperPassScan;

    /** 1, 2, 11 pages of paper passport*/
    private String paperPassScanFile;  // or photo

    private String paperPassSeries;

    private String paperPassNumber;

    @ManyToOne
    private Address addressOfRegistration;

}
