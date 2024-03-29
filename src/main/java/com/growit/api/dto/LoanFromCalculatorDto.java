package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoanFromCalculatorDto extends AbstractDto {

    private int amount;
    private int period;
    private String loanPurpose;

    @Size(max = 600)
    private String description;
}
