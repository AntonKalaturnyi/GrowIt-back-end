package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.growit.api.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserRegistrationDto extends AbstractDto {

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

    @Size(min = 6, max = 32)
    @NotBlank(message = "Please provide a password")
    private String password;

    @NotBlank(message = "Please provide a phone number")
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Please provide valid phone number")
    private String phone;


    public UserRegistrationDto (User user) {
        this.id = user.getLongId();
        this.name = user.getName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.password = null;
        this.phone = user.getPhone();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
    }
}
