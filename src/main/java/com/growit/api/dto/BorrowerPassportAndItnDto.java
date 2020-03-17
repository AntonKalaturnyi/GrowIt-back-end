package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerPassportAndItnDto extends InvestorPassportAndItnDto {

    private String region;
    private String district;
    private String postalCode;
    private String settlement;
    private String street;
    private String number;
    private String corpsNo;
    private String door;
}
