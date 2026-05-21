package com.tw.hotel.repository;

import com.tw.hotel.models.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
