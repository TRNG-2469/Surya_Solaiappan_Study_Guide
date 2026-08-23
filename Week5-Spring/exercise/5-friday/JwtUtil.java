package com.rev.rest.springg.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;
@Component
public class JwtUtil {
    private final SecretKey key = Keys.hmacShaKeyFor(
            "this-is-a-very-long-demo-secret-key-please-change-me-in-real-apps"
                    .getBytes());
    private final long expirationMs = 3600000;
    public String generateToken(UserDetails userDetails){
        String roles = userDetails.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expirationMs))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return parseClaims(token).getSubject();
    }

    public String extractRoles(String token){
        return parseClaims(token).get("roles", String.class);
    }

    private Claims parseClaims(String token){
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isExpired(String token){
        return parseClaims(token).getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }
}
