package com.vietqr.org.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
    private final static String HEADER = "Authorization";
    private final static String PREFIX = "Bearer ";
    private final static String SECRET = "mySecretKey";

    public static Claims parse(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
