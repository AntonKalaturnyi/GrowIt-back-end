package com.growit.api.exceptions;

import lombok.Data;

@Data
public class AccountOverdraftException extends RuntimeException {

    private String name;

    public AccountOverdraftException(String message) {
        super(message);
    }

    public AccountOverdraftException(String message, String name) {
        super(message);
        this.name = name;
    }
}
