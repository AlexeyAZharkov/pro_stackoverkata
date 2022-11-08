package com.javamentor.qa.platform.security.jwt;

import com.javamentor.qa.platform.models.entity.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationMs}")
    private int expirationMs;
    public String generateJwtToken(Authentication authentication){

        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date(
                        new Date().getTime() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateJwtToken(String jwtToken){
        Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
        return true;
    }

    public String getUsernameFromJwtToken(String jwtToken) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody().getSubject();
    }
}
