package com.cinema.cinemaserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

public class UserUtils {

    public static String createJWT(String email, String roleName) {
        String secretKey = "mySecretKey";

        boolean isAdmin=false;
        if(roleName.equals("ROLE_ADMIN")) isAdmin=true;

        String token=Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(email)
                .claim("admin",isAdmin)
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }

    public static Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("mySecretKey"))
                .parseClaimsJws(jwt.replace("\"", "")).getBody();

        return claims;
    }
}
