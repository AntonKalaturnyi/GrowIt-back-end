package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String photoWithPassportFileName;

    private String paperPassSeries;

    private String paperPassNumber;

    private String issuer;

    @ManyToOne
    @JoinColumn(name = "reg_address_id")
    private Address addressOfRegistration;

    @Column(name = "issue_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime issueDate;

/*    @ManyToOne
    private Address addressOfRegistration;*/

}
