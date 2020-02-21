package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestorPassportDto extends AbstractDto {

    private boolean idPassport;

    private String idPassNumber;

    private String paperPassSeries;

    private String paperPassNumber;
}
