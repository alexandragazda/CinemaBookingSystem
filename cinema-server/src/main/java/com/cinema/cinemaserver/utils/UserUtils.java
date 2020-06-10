package com.cinema.cinemaserver.utils;

import com.cinema.cinemaserver.domain.User;
import com.cinema.cinemaserver.domain.utils.Email;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.*;

public class UserUtils {

    public static String createJWT(User user, String roleName) {
        String secretKey = "mySecretKey";

        boolean isAdmin=false;
        if(roleName.equals("ROLE_ADMIN")) isAdmin=true;

        String token=Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getID())
                .claim("admin",isAdmin)
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

    public static String generateRandomPassword(){
        //generate a random number between 6-10, which will be the length of the password
        Random random = new Random();
        int min=6, max=10;
        int n = random.nextInt(max + 1 - min) + min;

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz" + "0123456789";

        String generatedString="";
        for (int i = 0; i < n; i++) {
            //generate a random number between 0-AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());
            generatedString+= String.valueOf(AlphaNumericString.charAt(index));
        }

        return generatedString;
    }

    public static void sendForgotPasswordEmail(String newPassword, String username) {
        String subject= "Reset your password";
        String message= "Hello!" + "\n\nThis is your new password: " + newPassword + "\n\n" + "Have a nice day!:)";
        Email email= new Email(username,subject,message);
        EmailUtils.sendMail(email); //send an email with the new password
    }
}
