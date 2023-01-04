package com.example.marks.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

public enum Role implements GrantedAuthority {
    GUEST, USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
