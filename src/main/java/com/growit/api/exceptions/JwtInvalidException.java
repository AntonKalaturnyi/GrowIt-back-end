package com.growit.api.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtInvalidException extends AuthenticationException {

    public JwtInvalidException(String msg) {
        super(msg);
    }
}
