package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestorPassportAndItnDto extends AbstractDto {

    protected boolean idPassport;
    protected String idPassNumber;
    protected String paperPassSeries;
    protected String paperPassNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    protected LocalDateTime issueDate;

    protected String issuer;
    protected String itnNumber;


    public InvestorPassportAndItnDto(boolean idPassport, String idPassNumber,
                                     String paperPassSeries, String paperPassNumber,
                                     LocalDateTime issueDate, String issuer,
                                     String itnNumber) {
        this.idPassport = idPassport;
        this.idPassNumber = idPassNumber;
        this.paperPassSeries = paperPassSeries;
        this.paperPassNumber = paperPassNumber;
        this.issueDate = issueDate;
        this.issuer = issuer;
        this.itnNumber = itnNumber;
    }
}
