package com.tw.hotel.repository;

import com.tw.hotel.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User getUserByUsername(String username);

}
