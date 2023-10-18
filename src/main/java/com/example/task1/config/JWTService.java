package com.example.task1.config;


import com.example.task1.entity.User;
import com.example.task1.service.UserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JWTService {
    public static final String SECRET = "e243ff66df6ab45aa9e0c22da3507e5d89820ed30b6260d3e445e6137eda43c1";
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }
    public String extractUsername(String jwt){
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaim(jwt);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaim(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
    }
    public boolean isTokenValid(String jwt, UserDetails userDetails){
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    public boolean isTokenExpired(String jwt) {
        return extractClaim(jwt,Claims::getExpiration).before(new Date());
    }

    public String regenerateToken(HashMap<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }
}
