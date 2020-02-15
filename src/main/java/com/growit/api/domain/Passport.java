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

    private boolean hasIdPassPhotoOrScan;

    private String idPassPhotoOrScan;

    private String idPassNumber;

    private boolean hasPaperPassPhotoOrScan;

    /** 1, 2, 11 pages of paper passport*/
    private String paperPassScanOrPhoto;  // or photo

    private String photoWithPassport;

    private String paperPassSeries;

    private String paperPassNumber;

    @ManyToOne
    private Address addressOfRegistration;

}
