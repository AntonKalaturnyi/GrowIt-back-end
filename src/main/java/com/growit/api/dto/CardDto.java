package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CardDto extends AbstractDto {

    private String number;

    private String expMonth;

    private String expYear;

    private String CVV;
}
