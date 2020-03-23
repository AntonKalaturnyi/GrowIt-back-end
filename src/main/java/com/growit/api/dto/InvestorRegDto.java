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

    @NotBlank(message = "Please provide a phone number")
    @Pattern(regexp = "^([5-9][0-9]\\d{7})$", message = "Please provide valid phone number")
    private String phone;

    public InvestorRegDto(@NotBlank(message = "Please, provide a name") String name,
                          @NotBlank(message = "Please, provide a surname") String lastName,
                          @NotBlank(message = "Please, provide a middle name") String middleName,
                          @NotBlank(message = "Please, provide your gender") String gender,
                          LocalDateTime birthday,
                          @NotBlank(message = "Please provide a phone number")
                          @Pattern(regexp = "^([5-9][0-9]\\d{7})$", message = "Please provide valid phone number") String phone) {
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
    }
}
