package com.growit.api.domain;

import com.growit.api.dto.UserRegistrationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "borrowers")
@EqualsAndHashCode(callSuper = false)
public class Borrower extends User {

    private boolean verified;

    private int monthlyIncomeOfficial;

    private int monthlyIncomeAdditional;

    @ManyToOne
    @JoinColumn(name = "address_id")
    protected Address address;

    @OneToOne
    protected ITN itn;

    @ManyToOne
    @JoinColumn(name = "work_sphere_id")
    private WorkSphere workSphere;

    @ManyToMany
    @JoinTable(name = "joint_borrower_employer",
            joinColumns = { @JoinColumn(name = "borrower_id") },
            inverseJoinColumns = { @JoinColumn(name = "employer_id")} )
    private Set<Employer> employers = new HashSet<>();

    private Boolean divorced;

    @Column(nullable = true)
    private Integer kidsBefore18yo;

    @Column(nullable = true)
    private Integer kidsAfter18yo;

    @OneToOne
    private CreditHistory creditHistory;

    private String homePhone;

    private String workPhone;

    private String fax;

    private String instagram;

    private String facebook;

    @ManyToOne
    private Education education;

    /** On front-end: Lang: UA, ENG
     Allowed types: employed; self-employed; entrepreneur*/
    private String workType;

    @Column(name = "spouse_itn")
    private String spouseITN;


    /** AFS (Anti Fraud System) UBKI */

    /* In case 'workType'is 'entrepreneur' */
    private String EDRPOUcode;

    @OneToOne
    private BorrowerVerification verification;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    Set<ContactPerson> contactPersons;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    Set<Loan> loans;

    @ManyToOne
    private HomeOwnership homeOwnership;

    @OneToOne
    private BorrowerAccount borrowerAccount;

    private String jobTitle;

    @OneToOne
    private CreditCard creditCard;

    public Borrower(User user) {
        super(user);
    }

    public Borrower(UserRegistrationDto dto) {
        super(dto);
    }

}
