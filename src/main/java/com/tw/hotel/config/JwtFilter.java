package com.tw.hotel.config;

import com.tw.hotel.services.AuthService;
import com.tw.hotel.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {
  private final Logger logger;
  private JwtService jwtService;
  private AuthService userDetailService;

  public JwtFilter(JwtService jwtService, AuthService userDetails, Logger logger) {
    this.jwtService = jwtService;
    this.userDetailService = userDetails;
    this.logger = logger;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    System.out.printf(">>> %s", authHeader);
    if (authHeader == null ||
            !authHeader.startsWith("Bearer ")) {

      filterChain.doFilter(request, response);
      return;
    }
    String jwt = authHeader.substring(7);
    String username = jwtService.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

}
