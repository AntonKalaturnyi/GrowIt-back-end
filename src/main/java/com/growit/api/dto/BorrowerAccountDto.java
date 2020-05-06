package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BorrowerAccountDto extends AbstractDto {

    private double availableBalance;
    private String dailyRate;
    private String rank;
    private String score;
    private String status;

    public BorrowerAccountDto() {
        this.availableBalance = 0;
        this.dailyRate = "-";
        this.rank = "-";
        this.score = "-";
        this.status = "no current loans";
    }
}
