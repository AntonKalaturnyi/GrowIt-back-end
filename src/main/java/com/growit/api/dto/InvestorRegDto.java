package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestorRegDto extends AbstractDto {

    @NotBlank(message = "Please, provide a name")
    private String name;

    @NotBlank(message = "Please, provide a surname")
    private String lastName;

    @NotBlank(message = "Please, provide a middle name")
    private String middleName;

    @NotBlank(message = "Please, provide your gender")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime birthday;

    @NotBlank(message = "Please, provide an email")
    @Email(message = "Please, provide valid email")
    private String email;

    @NotBlank(message = "Please provide a phone number")
    @Pattern(regexp = "^([5-9][0-9]\\d{7})$", message = "Please provide valid phone number")
    private String phone;
}
