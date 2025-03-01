package com.example.Backend.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Data
@Service
public class JwtService {

    public static final String SECRET = "0a6f2f8f3f7c67e8d8b2e4c4b1d8b7f0a3c4e5b6f7d8a9b0c2a1f4e5d6a7b8c9\n";

    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .signWith(getKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
         try{
             Jwts.parser()
                     .verifyWith(getKey())
                     .build()
                     .parseSignedClaims(token);
             return true;
         } catch (Exception e) {
             return false;
         }

    }
}
