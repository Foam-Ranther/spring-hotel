package com.tw.hotel.services;

import com.tw.hotel.dto.UserRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.ConcurrentHashMap;

public class AuthService implements UserDetailsService {
    private final ConcurrentHashMap<String, UserDetails> users;
  private final PasswordEncoder passwordEncoder;

  public AuthService(ConcurrentHashMap<String, UserDetails> users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
  }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User Details");
        }
        return user;
    }

    public String registerUser(UserRequest userRequest) {
        UserDetails user = User
                .builder()
                .passwordEncoder(passwordEncoder::encode)
                .password(userRequest.password())
                .username(userRequest.username())
                .build();
        users.put(user.getUsername(), user);
        return user.getUsername();
    }

    public boolean loginUser(UserRequest user) {
      return users.containsKey(user.username());
    }
}
