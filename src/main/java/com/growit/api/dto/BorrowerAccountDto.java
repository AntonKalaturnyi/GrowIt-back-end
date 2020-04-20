package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerAccountDto extends AbstractDto {

    private double availableBalance;
    private double dailyRate;
    private String rank;
    private int score;
    private String status;

}
