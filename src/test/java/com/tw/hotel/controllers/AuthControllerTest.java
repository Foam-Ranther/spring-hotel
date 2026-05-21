package com.tw.hotel.controllers;

import com.tw.hotel.dto.UsersRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureRestTestClient
class AuthControllerTest {

  @Autowired
  private RestTestClient client;

  @Test
  void testForLoginPage() {
    AuthController authController = new AuthController();
    String responseBody = client.post()
            .uri("/api/users/login")
            .body(new UsersRequest("yash", "yash@1234"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .returnResult().getResponseBody();
    assertEquals("hello", responseBody);

  }
}