package com.tw.hotel.controllers;

import com.tw.hotel.dto.UserRequest;
import com.tw.hotel.services.AuthService;
import com.tw.hotel.services.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/api/users")
public class AuthController {
  private static final long EXPIRATION_TIME = 3600;
  private final AuthService authService;
  private JwtService jwtService;

  public AuthController(AuthService authService, JwtService jwtService)  {
    this.authService = authService;
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login (@RequestBody UserRequest user) {
    if (authService.loginUser(user)) {
      String token = jwtService.generateToken(user.username());
      ResponseCookie cookie = ResponseCookie.from("jwt", token)
              .httpOnly(true)
              .secure(true)
              .sameSite("Strict")
              .path("/")
              .maxAge(Duration.ofMillis(EXPIRATION_TIME))
              .build();
      return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    };
    return null;
  }
  @PostMapping("/register")
  public String register (@RequestBody UserRequest user) {
    return "hello";
  }
}
