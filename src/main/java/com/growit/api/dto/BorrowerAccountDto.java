package com.growit.api.dto;

import lombok.Data;

@Data
public class BorrowerAccountDto extends AbstractDto {

    private double availableBalance;

    private String currency;

    private long borrowerId;
}
