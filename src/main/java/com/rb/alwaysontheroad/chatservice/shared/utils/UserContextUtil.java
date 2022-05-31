package com.rb.alwaysontheroad.chatservice.shared.utils;

import com.rb.alwaysontheroad.chatservice.shared.exception.NoSecurityContextException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserContextUtil {
    private static final String ID = "sub";
    private static final String USERNAME = "preferred_username";

    @NotNull
    public static UUID getCurrentUserId(@Nullable Jwt jwt) {
        return UUID.fromString(getByAttribute(jwt, ID));
    }

    @NotNull
    public static UUID getCurrentUserUsername(@Nullable Jwt jwt) {
        return UUID.fromString(getByAttribute(jwt, USERNAME));
    }

    @NotNull
    private static String getByAttribute(@Nullable Jwt jwt, String attribute) {
        return Optional.ofNullable(jwt)
                .map(token -> {
                    @Nullable Object o = token.getClaims().get(attribute);
                    Objects.requireNonNull(o);
                    return (String) o;
                })
                .orElseThrow(() -> new NoSecurityContextException("Security context is not initialized"));
    }
}
