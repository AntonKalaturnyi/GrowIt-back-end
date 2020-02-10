package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerAccountDto extends AbstractDto {

    private double availableBalance;

    private String currency;

    private long borrowerId;
}
