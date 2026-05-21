package com.tw.hotel.services;

import com.tw.hotel.dto.UserRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.concurrent.ConcurrentHashMap;

public class AuthService implements UserDetailsService {
  private final ConcurrentHashMap<String, UserDetails> users;

  public AuthService(ConcurrentHashMap<String, UserDetails> users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = this.users.get(username);
    if(user == null) {
      throw new UsernameNotFoundException("Invalid User Details");
    }
    return user;
  }

  public String registerUser(UserRequest userRequest) {
    UserDetails user = User
            .builder()
            .password(userRequest.password())
            .username(userRequest.username())
            .build();
    users.put(user.getUsername(), user);
    return user.getUsername();
  }
}
