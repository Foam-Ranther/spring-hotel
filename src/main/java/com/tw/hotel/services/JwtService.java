package com.tw.hotel.services;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Service
public class JwtService {

  @Value("${security.jwt.expiration-time}")
  private long expiryTime;
  @Value("${security.jwt.secret-key}")
  private String SECRET;

  public String generateToken(String username) {
    return Jwts
            .builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiryTime))
            .signWith(getSigningKey())
            .compact();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET.getBytes());
  }
}
