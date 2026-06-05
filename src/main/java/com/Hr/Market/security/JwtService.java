package com.Hr.Market.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
@Service
public class JwtService
{
    private final  String Secret_Key = "mySuperSecretKeyForJwtGeneration123456" ;

    private Key getSigningKey ()
    {
        return Keys.hmacShaKeyFor(Secret_Key.getBytes()) ;
    }
    public  String generateToken (String username , List<String> roles)
    {
        return Jwts.
                builder()
                .setSubject(username)
                .claim("roles" , roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*60))
                .signWith(getSigningKey())
                .compact() ;
    }
    public Claims extractallclaims (String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody() ;
    }
    public  String extractUserName (String token)
    {
        return  extractallclaims(token).getSubject() ;
    }
    public List<String> extractRoles (String token)
    {
        return  extractallclaims(token).get("roles",List.class) ;
    }
    public boolean IsTokenVaild (String username , String token)
    {
        return extractUserName(token).equals(username) && !isTokenExpired(token) ;
    }
    private boolean isTokenExpired(String token) {
        return extractallclaims(token).getExpiration().before(new Date());
    }
}
