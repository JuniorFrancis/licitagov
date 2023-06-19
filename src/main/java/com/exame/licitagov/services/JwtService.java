package com.exame.licitagov.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    public String extractUsername(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    public String generateToken(UserDetails userDetails);
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    private Claims extractAllClaims(String token) {
        return null;
    }

    private Key getSignInKey() {
        return null;
    }

    default boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    private boolean isTokenExpired(String token) {
        return false;
    }

    private Date extractExpiration(String token) {
        return null;
    }

}
