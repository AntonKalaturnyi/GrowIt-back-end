package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerPassportAndItnDto extends InvestorPassportAndItnDto {

    private String fileName;
    private String region;
    private String district;
    private String postalCode;
    private String settlement;
    private String street;
    private String number;
    private String corpsNo;
    private String door;


    public BorrowerPassportAndItnDto(boolean idPassport, String idPassNumber,
                                     String paperPassSeries, String paperPassNumber,
                                     LocalDateTime issueDate, String issuer, String itnNumber,
                                     String region, String district, String postalCode,
                                     String settlement, String street, String number,
                                     String corpsNo, String door, String fileName) {

        super(idPassport, idPassNumber, paperPassSeries, paperPassNumber, issueDate, issuer, itnNumber);
        this.region = region;
        this.district = district;
        this.postalCode = postalCode;
        this.settlement = settlement;
        this.street = street;
        this.number = number;
        this.corpsNo = corpsNo;
        this.door = door;
        this.fileName = fileName;
    }
}
