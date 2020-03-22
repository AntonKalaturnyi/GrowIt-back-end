package com.growit.api.domain;

import com.growit.api.dto.UserRegistrationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "investors")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Investor extends User {

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<Investment> investments;

/*    @Column(name = "invested_before")
    private boolean investedBefore;*/

/*    @ManyToOne
    private InvestmentType investedTo;*/

    @OneToOne
    private InvestorAccount account;

    private String itn;

    public Investor(UserRegistrationDto dto) {
        super(dto);
    }

    public Investor(Borrower borrower) {
        this.name = borrower.getName();
        this.middleName = borrower.getMiddleName();
        this.lastName = borrower.getLastName();
        this.userpic = borrower.getUserpic();
        this.gender = borrower.getGender();
        this.birthday = borrower.getBirthday();
        this.age = borrower.getAge();
        this.email = borrower.getEmail();
        this.phone = borrower.getPhone();
        this.created = LocalDateTime.now();
        this.updated = this.created;
        this.lastVisit = this.created;
    }

    @Override
    public String toString() {
        return "Investor{" +
                "name='" + name + '\'' +
                ", userpic='" + userpic + '\'' +
                ", gender='" + gender + '\'' +
                ", locale='" + locale + '\'' +
                ", lastVisit=" + lastVisit +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", passport=" + passport +
                ", married=" + married +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                ", active=" + active +
                ", birthday=" + birthday +
                '}';
    }
}
