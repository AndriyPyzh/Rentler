package com.rentler.auth.util;

import com.rentler.auth.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Data
@Component
public class Jwt {
    private final Integer accessTokenValidTimeInMinutes = 120;
    private final String accessTokenKey = "MyKey123MyKey123MyKey123MyKey123MyKey123MyKey123";

    public String createAccessToken(String username, Role role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", Collections.singleton(role.name()));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, accessTokenValidTimeInMinutes);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, accessTokenKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        boolean isValid = false;
        try {
            Jwts.parser().setSigningKey(accessTokenKey).parseClaimsJws(token);
            isValid = true;
        } catch (Exception e) {
            log.info("Given token is not valid: " + e.getMessage());
        }
        return isValid;
    }
}