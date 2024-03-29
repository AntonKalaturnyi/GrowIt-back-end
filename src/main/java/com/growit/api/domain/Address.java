package com.growit.api.domain;

import com.growit.api.util.ConstantUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@Table(name = "address")
@EqualsAndHashCode(callSuper = false)
public class Address extends AbstractEntity {

    @Column(name = "postal_code", nullable = false)
    @Pattern(regexp = ConstantUtil.VALID_POSTAL_CODE_REGEXP, message = "invalid postal code")
    private String postalCode;

    @Column(name = "door", nullable = false)
    private String door;

    @Column(name = "corps", nullable = true)
    private String corpsNo;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "settlement", nullable = false)
    private String settlement;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "region", nullable = false)
    private String region;

/*    @Column(name = "country", nullable = false)
    private String country;*/

    public Address(String region, String district, String postalCode, String settlement, String street, String number,
                   String corpsNo, String door) {
        this.postalCode = postalCode;
        this.region = region;
        this.district = district;
        this.settlement = settlement;
        this.street = street;
        this.number = number;
        this.corpsNo = corpsNo;
        this.door = door;
    }

}
