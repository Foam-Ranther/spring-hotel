package com.tw.hotel.services;

import com.tw.hotel.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthServiceTest {
    @Test
    void shouldRegisterThenUserWhenProvidedWithUserNameAndPassword() {
        ConcurrentHashMap<String, UserDetails> users = new ConcurrentHashMap<>();
        AuthService authService = new AuthService(users);

        String response = authService.registerUser(new UserRequest("test", "test@1234"));

        assertEquals("test", response);

    }

    @Test
    void shouldLoginUserWhenProvidedWithRightUserNameAndPassword() {
        ConcurrentHashMap<String, UserDetails> users = new ConcurrentHashMap<>();
        UserDetails user = User.builder().username("test").password("test@1234").build();
        users.put(user.getUsername(), user);
        AuthService authService = new AuthService(users);
        UserRequest userRequest = new UserRequest("test", "test@1234");
        UserDetails response = authService.loadUserByUsername(userRequest.username());
        assertEquals(user, response);

<<<<<<< Updated upstream
    }

    @Test
    void shouldThrowErrorLoginUserWhenProvidedWithInvalidUserNameAndPassword() {
        ConcurrentHashMap<String, UserDetails> users = new ConcurrentHashMap<>();
//    UserDetails user = User.builder().username("test").password("test@1234").build();
        AuthService authService = new AuthService(users);
        UserRequest userRequest = new UserRequest("test", "test@1234");
        assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername(userRequest.username()));
    }
=======
  @Test
  void shouldThrowErrorLoginUserWhenProvidedWithInvalidUserNameAndPassword() {
    ConcurrentHashMap<String, UserDetails> users = new ConcurrentHashMap<>();
    AuthService authService = new AuthService(users);
    UserRequest userRequest = new UserRequest("test", "test@1234");
    assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername(userRequest.username()));
  }
>>>>>>> Stashed changes
}