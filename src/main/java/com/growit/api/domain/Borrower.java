package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.growit.api.dto.UserRegistrationDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "borrowers")
public class Borrower extends User {

    private boolean verified;

    @Column(nullable = true)
    private Double dailyLoanRate;

    private String safetyRank;

    @Column(nullable = true)
    private Integer verificationScore;

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

    @Column(name = "registration_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime registrationDate; // setted after verification is Done

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

    private String instagram;

    private String facebook;

    @ManyToOne
    private Education education;

    /** AFS (Anti Fraud System) UBKI */

    /* In case 'workType'is 'entrepreneur'
    private String EDRPOUcode;
     */

    @OneToOne
    private BorrowerVerification verification;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    List<ContactPerson> contactPersons;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    Set<Loan> loans;

    @Column(nullable = true)
    private String homeType;

    @OneToOne
    private BorrowerAccount borrowerAccount;

    @OneToOne
    private CreditCard creditCard;

    @Column(nullable = true)
    private Boolean personalFilled;

    @Column(nullable = true)
    private Boolean docsFilled;

    @Column(nullable = true)
    private Boolean addressFilled;

    @Column(nullable = true)
    private Boolean employmentFilled;

    @Column(nullable = true)
    private Boolean educationFilled;

    @Column(nullable = true)
    private Boolean assetsFilled;


    public Borrower(User user) {
        super(user);
    }

    public Borrower(UserRegistrationDto dto) {
        super(dto);
    }






}
