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

    private String number;

    private String street;

    private String settlement;

    private String district;

    private String region;

    private String country;

    private boolean sameAddressInPassport;
}
