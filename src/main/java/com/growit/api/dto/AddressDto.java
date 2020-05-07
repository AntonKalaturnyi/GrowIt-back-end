package com.growit.api.dto;

import com.google.common.base.Objects;
import com.growit.api.util.ConstantUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
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

    private String homeType;

    private boolean sameAddressInPassport;

    public AddressDto(@Pattern(regexp = ConstantUtil.VALID_POSTAL_CODE_REGEXP, message = "invalid postal code")
                              String postalCode, String door, String corpsNo,
                      String number, String street, String settlement, String district,
                      String region, String homeType, boolean sameAddressInPassport) {

        this.postalCode = postalCode;
        this.door = door;
        this.corpsNo = corpsNo;
        this.number = number;
        this.street = street;
        this.settlement = settlement;
        this.district = district;
        this.region = region;
        this.homeType = homeType;
        this.sameAddressInPassport = sameAddressInPassport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equal(getPostalCode(), that.getPostalCode()) &&
                Objects.equal(getDoor(), that.getDoor()) &&
                Objects.equal(getCorpsNo(), that.getCorpsNo()) &&
                Objects.equal(getNumber(), that.getNumber()) &&
                Objects.equal(getStreet(), that.getStreet()) &&
                Objects.equal(getSettlement(), that.getSettlement()) &&
                Objects.equal(getDistrict(), that.getDistrict()) &&
                Objects.equal(getRegion(), that.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getPostalCode(), getDoor(), getCorpsNo(), getNumber(), getStreet(), getSettlement(), getDistrict(), getRegion());
    }
}
