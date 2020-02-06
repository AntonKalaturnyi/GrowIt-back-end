package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "contact_person")
@EqualsAndHashCode(callSuper = false)
public class ContactPerson extends AbstractEntity {

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    private String phone;

    /** On front-end: Lang: UA, ENG
     Allowed types: colleague; friend; relative*/
    private String relationship;

    @ManyToOne
    private Borrower borrower;

    @OneToOne
    protected ITN itn;
}
