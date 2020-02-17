package com.growit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto implements Serializable {

    @NotBlank(message = "Please, provide an email")
    @Email(message = "Please, provide valid email")
    private String username;

    @Size(min = 6, max = 32)
    @NotBlank(message = "Please provide a password")
    private String password;
}
