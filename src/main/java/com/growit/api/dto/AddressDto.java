package com.growit.api.dto;

import com.growit.api.util.ConstantUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddressDto extends AbstractDto {

    @Pattern(regexp = ConstantUtil.VALID_POSTAL_CODE_REGEXP, message = "invalid postal code")
    private String postalCode;

    private String door;

    private String corpsNo;

    private String number;

    private String street;

    private String settlement;

    private String district;

    private String region;

    private boolean sameAddressInPassport;

    public AddressDto(@Pattern(regexp = ConstantUtil.VALID_POSTAL_CODE_REGEXP, message = "invalid postal code")
                              String postalCode, String door, String corpsNo,
                      String number, String street, String settlement, String district,
                      String region, boolean sameAddressInPassport) {

        this.postalCode = postalCode;
        this.door = door;
        this.corpsNo = corpsNo;
        this.number = number;
        this.street = street;
        this.settlement = settlement;
        this.district = district;
        this.region = region;
        this.sameAddressInPassport = sameAddressInPassport;
    }
}
