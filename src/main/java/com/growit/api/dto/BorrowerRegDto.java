package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerRegDto extends InvestorRegDto {

    private String maritalStatus;
    private Integer kidsBefore18yo;
    private Integer  kidsAfter18yo;
    private String instagram;
    private String facebook;
}
