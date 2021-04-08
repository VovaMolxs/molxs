package com.chat.molxs.repository;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
