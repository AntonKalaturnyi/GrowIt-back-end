package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerAccountLoanDto extends AbstractDto {

    private String status;
    private int amount;
    private String term;
    private double dailyLoanRate;

    /** format: [rank]-[score]*/
    private String rating;
    private String loanPurpose;
    private LocalDate closeDate;

    /** Change of rating compared to previous loan terms. format: [+/-]int */
    private String ratingChange;

}
