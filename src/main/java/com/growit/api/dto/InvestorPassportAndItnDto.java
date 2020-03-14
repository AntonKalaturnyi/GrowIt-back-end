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

    private boolean idPassport;
    private String idPassNumber;
    private String paperPassSeries;
    private String paperPassNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime issueDate;

    private String issuer;
    private String itnNumber;

    @NotBlank(message = "Please, provide an email")
    @Email(message = "Please, provide valid email")
    private String email;
}
