package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "public_records")
@EqualsAndHashCode(callSuper = false)
public class PublicRecord extends AbstractEntity {

    private String name;

    private LocalDate date;
}

/**
 bankruptcy,
 judgments,
 liens,
 lawsuits,
 foreclosures)
 */
