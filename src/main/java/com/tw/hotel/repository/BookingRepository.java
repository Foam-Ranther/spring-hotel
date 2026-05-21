package com.tw.hotel.repository;

import com.tw.hotel.models.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findBookingsByUserId(String userId);
}
