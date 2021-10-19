package ru.tyreservice.aggregator.enums;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    @Hidden
    CLIENT, PARTNER, MANAGER, ADMIN;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
