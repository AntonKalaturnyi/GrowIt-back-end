package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Individual Taxpayer Number
 * */
@Data
@Entity
@NoArgsConstructor
@Table(name = "itn")
@EqualsAndHashCode(callSuper = false)
public class ITN extends AbstractEntity {

    @Column(name = "has_itn_scan")
    private boolean hasITNScan;

    @Column(name = "itn_file")
    private String ITN_file;

    @Column(name = "itn_digits")
    protected String  ITN_digits;
}
