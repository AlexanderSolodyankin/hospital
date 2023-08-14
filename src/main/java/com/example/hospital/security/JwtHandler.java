package com.example.hospital.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Value("${jwt.token.expired}")
    private Long timeTokenExpired;


    public String generationToken(Authentication authentication){
        Date dateNow = new Date();
        Date dateExpiredToken = new Date(dateNow.getTime() + timeTokenExpired);
        UserDetails userAuthorized = (UserDetails) authentication.getPrincipal();

        return Jwts
                .builder()
                .setIssuedAt(dateNow)
                .setExpiration(dateExpiredToken)
                .setSubject(userAuthorized.getUsername())
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    public String getUserNameFromToken(String token){


        return Jwts
                .parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validationToken(String token){

        try {
            Jwts.parser().setSigningKey(this.secretKey).parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Время действия токена истекло. Обновите токен");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Неправелно сформерованный токен");
        } catch (SignatureException e) {
            throw new RuntimeException("Неверная подпись токена");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Токен не должен быть пустым");
        }
    }
}
