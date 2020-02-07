package com.growit.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuthDto implements Serializable {

    private String username;
    private String password;

    public AuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
