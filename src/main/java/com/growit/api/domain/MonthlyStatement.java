package com.growit.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "monthly_statement")
@EqualsAndHashCode(callSuper = false)
public class MonthlyStatement extends AbstractEntity {

    @ManyToOne
    private InvestorAccount account;

    private double remainingBalanceStartOfMo;

    private double remainingBalanceEndOfMo;

    private double portfolioChange;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime endDate;

}
