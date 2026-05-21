package com.tw.hotel.controllers;

import com.tw.hotel.dto.UsersRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {
  @PostMapping("/login")
  public String login (@RequestBody UsersRequest user) {
    return "hello";
  }
}
