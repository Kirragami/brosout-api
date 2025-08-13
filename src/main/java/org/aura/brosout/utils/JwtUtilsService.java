package org.aura.brosout.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtUtilsService {

    private final SecretKey secretKey;

    private final Long expiration;

    private final Long refreshExpiration;

    public JwtUtilsService(
            @Value("${application.security.jwt.secret-key}") String jwtSecret,
            @Value("${application.security.jwt.expiration}") Long expiration,
            @Value("${application.security.jwt.refresh-token.expiration}") Long refreshExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
    }

    public String generateToken(String username, Map<String, Object> claims, Long expiration) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(secretKey)
                .setExpiration(new Date(now.getTime() + expiration))
                .setIssuedAt(now)
                .compact();
    }

    public String generateAccessToken(String username) {
        return generateToken(username, Map.of(), expiration);
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, Map.of(), refreshExpiration);
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> extractor) {
        Claims claims = parseClaims(token);
        return extractor.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username) {
        try {
            String sub = extractUsername(token);
            return sub.equals(username) && !extractClaims(token, Claims::getExpiration).before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

}
