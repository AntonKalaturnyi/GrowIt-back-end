package com.growit.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.growit.api.domain.Investor;
import com.growit.api.domain.Role;
import com.growit.api.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

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

    private String userpic;

    private int age;

    @NotBlank(message = "Please, provide your gender")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime birthday;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime lastVisit;

    @NotBlank(message = "Please, provide an email")
    @Email(message = "Please, provide valid email")
    private String email;

    @NotBlank(message = "Please provide a phone number")
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Please provide valid phone number")
    private String phone;

    private Set<Role> roles;


    public UserRegistrationDto (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.lastVisit = user.getLastVisit();
        this.email = user.getEmail();
        this.userpic = user.getUserpic();
//        this.age = user.getAge();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
    }

    public UserRegistrationDto (Investor user) {
        this.id = user.getId();
        this.lastVisit = user.getLastVisit();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
    }
}
