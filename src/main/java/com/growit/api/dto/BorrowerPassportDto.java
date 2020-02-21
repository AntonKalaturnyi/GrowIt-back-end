package com.growit.api.dto;

import com.growit.api.util.ConstantUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerPassportDto extends AbstractDto {

    @NotNull(groups = {New.class},
            message = "Please specify id of borrower")
    private long borrowerId;

    private long passportId;

    private long addressId;

    @NotNull(groups = {New.class},
            message = "Please specify if you have ID passport")
    private boolean idPassport;

    private boolean hasIdPassPhotoOrScan;

    private String idPassPhotoOrScan;

    private String idPassNumber;

    /** 1, 2, 11 pages of paper passport*/

    private String paperPassScanOrPhoto;  // or photo

    @NotNull(groups = {New.class},
            message = "Please provide photo with passport")
    private String photoWithPassport;

    private String paperPassSeries;

    private String paperPassNumber;

    // Address

    @Pattern(regexp = ConstantUtil.VALID_POSTAL_CODE_REGEXP, message = "invalid postal code")
    private String postalCode;

    private String door;

    @NotNull(groups = {New.class},
            message = "Please specify a house number of registration address")
    private String number;

    @NotNull(groups = {New.class,},
            message = "Please specify a street of registration address")
    private String street;

    @NotNull(groups = {New.class},
            message = "Please specify a city or village name")
    private String settlement;

    private String district;

    private String region;

    @NotNull(groups = {New.class},
            message = "Please specify a country of registration")
    private String country;
}
