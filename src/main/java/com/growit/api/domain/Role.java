package com.growit.api.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    REGISTERED_USER, INVESTOR, BORROWER, VERIFIED_BORROWER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return name();
    }
}

// SET RILES ON REGISTRATION
