package com.tw.hotel.services;

import com.tw.hotel.dto.UserRequest;
import com.tw.hotel.models.User;
import com.tw.hotel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService implements UserDetailsService {
  private final PasswordEncoder passwordEncoder;
  private UserRepository userRepository;

  public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User Details");
        }
      UserDetails userDetail = org.springframework.security.core.userdetails.User
              .builder()
              .username(username)
              .passwordEncoder(passwordEncoder::encode)
              .build();
      return userDetail;
    }

    public String registerUser(UserRequest userRequest) {
      User user = new User(userRequest.username(), userRequest.password());
      userRepository.save(user);
      return "successfully registered";
    }

    public boolean loginUser(UserRequest user) {
      User userByUsername = userRepository.getUserByUsername(user.username());
      if (userByUsername == null) { throw new UsernameNotFoundException("Invalid username");};
      return true;
    }

}
