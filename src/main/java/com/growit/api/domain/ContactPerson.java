package com.growit.api.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_person")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactPerson)) return false;
        if (!super.equals(o)) return false;
        ContactPerson that = (ContactPerson) o;
        return Objects.equal(getFirstName(), that.getFirstName()) &&
                Objects.equal(getLastName(), that.getLastName()) &&
                Objects.equal(getMiddleName(), that.getMiddleName()) &&
                Objects.equal(getPhone(), that.getPhone()) &&
                Objects.equal(getRelationship(), that.getRelationship()) &&
                Objects.equal(getBorrower().getItn(), that.getBorrower().getItn());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getFirstName(), getLastName(), getMiddleName(), getPhone(), getRelationship(), getBorrower().getItn());
    }
}
