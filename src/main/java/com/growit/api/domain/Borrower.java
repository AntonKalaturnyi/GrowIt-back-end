package com.growit.api.domain;

import com.growit.api.dto.UserRegistrationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private int monthlyIncomeTotal;

    private String additionalIncomeSource;

    @Column(nullable = true)
    private Integer scholarship;

    @Column(nullable = true)
    private Integer pension;

    @Column(nullable = true)
    private Integer employeesCount;

    @ManyToOne
    @JoinColumn(name = "address_id")
    protected Address address;

/*    @OneToOne
    protected ITN itn;*/

    private String itn;

    /*   @ManyToMany
       @JoinTable(name = "joint_borrower_employer",
               joinColumns = { @JoinColumn(name = "borrower_id") },
               inverseJoinColumns = { @JoinColumn(name = "employer_id")} )
       private Set<Employer> employers = new HashSet<>();*/

    @OneToOne
    private Employment employment;

    @Column(nullable = true)
    private String flat;

    @Column(nullable = true)
    private Boolean hasHouse;

    @Column(nullable = true)
    private Boolean hasCar;

    @Column(nullable = true)
    private String carAge;

    @Column(nullable = true)
    private Boolean wasAbroad;

    @Column(nullable = true)
    private Integer monthlyExpenses;

    @Column(nullable = true)
    private Integer monthlyObligations;

    @ManyToOne
    private SocialStatus socialStatus;

    private String maritalStatus;

    @Column(nullable = true)
    private String kidsBefore18yo;

    @Column(nullable = true)
    private String kidsAfter18yo;

    @OneToOne
    private CreditHistory creditHistory;

//    private String workPhone;

    private String instagram;

    private String facebook;

    @ManyToOne
    private Education education;

    /** On front-end: Lang: UA, ENG
     Allowed types: employed; self-employed; entrepreneur*/
    private String workType;

/*    @Column(name = "spouse_itn")
    private String spouseITN;*/


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

//    private String jobTitle;

    @OneToOne
    private CreditCard creditCard;

    public Borrower(User user) {
        super(user);
    }

    public Borrower(UserRegistrationDto dto) {
        super(dto);
    }

}
