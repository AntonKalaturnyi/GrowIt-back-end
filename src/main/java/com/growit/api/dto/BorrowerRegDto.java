package com.growit.api.dto;

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
public class BorrowerRegDto extends InvestorRegDto {

    private String maritalStatus;
    private String kidsBefore18yo;
    private String kidsAfter18yo;
    private String instagram;
    private String facebook;


    public BorrowerRegDto(@NotBlank(message = "Please, provide a name") String name,
                          @NotBlank(message = "Please, provide a surname") String lastName,
                          @NotBlank(message = "Please, provide a middle name") String middleName,
                          @NotBlank(message = "Please, provide your gender") String gender,
                          LocalDateTime birthday,
                          @NotBlank(message = "Please, provide an email")
                          @NotBlank(message = "Please provide a phone number") @Pattern(regexp = "^([5-9][0-9]\\d{7})$",
                                  message = "Please provide valid phone number") String phone,
                          String maritalStatus,
                          String kidsBefore18yo,
                          String kidsAfter18yo,
                          String instagram,
                          String facebook) {

        super(name, lastName, middleName, gender, birthday, phone);
        this.maritalStatus = maritalStatus;
        this.kidsBefore18yo = kidsBefore18yo;
        this.kidsAfter18yo = kidsAfter18yo;
        this.instagram = instagram;
        this.facebook = facebook;
    }
}
