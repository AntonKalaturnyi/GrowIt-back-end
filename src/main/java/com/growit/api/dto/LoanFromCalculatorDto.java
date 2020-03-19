package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoanFromCalculatorDto extends AbstractDto {

    private int amount;
    private int period;
    private String loanPurpose;
}
