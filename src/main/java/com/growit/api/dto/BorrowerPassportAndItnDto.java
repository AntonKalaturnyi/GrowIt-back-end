package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime issueDate;


}
