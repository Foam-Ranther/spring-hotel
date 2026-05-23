package com.tw.hotel.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User{
  private final String username;
  private final String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
