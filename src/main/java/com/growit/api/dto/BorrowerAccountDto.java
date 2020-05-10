package com.growit.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BorrowerAccountDto extends AbstractDto {

    private double availableBalance;
    private String dailyRate;
    private String rank;
    private String score;
    private String status;
    private boolean verified;


    public BorrowerAccountDto(double availableBalance, String dailyRate, String rank, String score, String status, boolean verified) {
        this.availableBalance = availableBalance;
        this.dailyRate = dailyRate;
        this.rank = rank;
        this.score = score;
        this.status = status;
        this.verified = verified;
    }

    public BorrowerAccountDto() {
        this.availableBalance = 0;
        this.dailyRate = "-";
        this.rank = "-";
        this.score = "-";
        this.status = "no current loans";
        this.verified = false;
    }
}
