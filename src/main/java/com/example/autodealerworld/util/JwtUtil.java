package com.example.autodealerworld.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.access-token.ttl-millis}")
    private long accessTokenTtlMillis;

    @Value("${jwt.refresh-token.ttl-millis}")
    private long refreshTokenTtlMillis;

    private Key key;

    private JwtParser jwtParser;

    @PostConstruct
    public void setUpKey() {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    private String generateToken(String username, long ttlMillis, Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(UserDetails user) {
        List<String> roles = user
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Set<String> permissions = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("permissions", permissions);

        return generateToken(user.getUsername(), accessTokenTtlMillis, claims);
    }


    public String generateRefreshToken(UserDetails user) {
        return generateToken(user.getUsername(), refreshTokenTtlMillis, Collections.emptyMap());
    }

    public boolean isTokenExpired(String token) {
        Date expireAt = extractFromToken(token, Claims::getExpiration);
        return !expireAt.after(new Date());
    }

    public String extractUsername(String token) {
        return extractFromToken(token, Claims::getSubject);
    }

    public <T> T extractFromToken(String token, Function<Claims, T> extractor) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return extractor.apply(claims);
    }
}