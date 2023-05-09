package ru.kpfu.itis.technodanyaspring.model;

import lombok.*;
import org.springframework.security.core.*;


@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}