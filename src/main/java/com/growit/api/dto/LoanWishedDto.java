package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoanWishedDto extends AbstractDto {

    @Max(groups = {New.class, Existing.class},
            value = 35000, message = "Amount can't be more than 35000 UAH")
    @Min(groups = {New.class, Existing.class},
            value = 1000, message = "Amount can't be less than 1000 UAH")
    private int wishedAmount;

    @Min(groups = {New.class, Existing.class},
            value = 7, message = "Period can't be less than 7 days")
    private int wishedPeriod;



}
