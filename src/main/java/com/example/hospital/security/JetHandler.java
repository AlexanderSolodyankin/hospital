package com.example.hospital.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JetHandler {

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Value("${jwt.token.expired}")
    private Long expiredTime;

    public String generateToken(Authentication authentication) {
        Date dateNow = new Date();
        Date expiredTimeAuthToken = new Date(dateNow.getTime() + this.expiredTime);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Jwts
                .builder()
                .setIssuedAt(dateNow)
                .setExpiration(expiredTimeAuthToken)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validationToken(String token) {
        try {

            Jwts.parser().setSigningKey(this.secretKey).parse(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
