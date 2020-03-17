package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Please, provide an email")
    @Email(message = "Please, provide valid email")
    protected String email;
}
