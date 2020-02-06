package com.growit.api.domain;

import com.growit.api.dto.UserRegistrationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

}
