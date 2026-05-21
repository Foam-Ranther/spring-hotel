package com.tw.hotel.service;

import com.tw.hotel.models.Booking;
import com.tw.hotel.models.Hotel;
import com.tw.hotel.repository.BookingRepository;
import com.tw.hotel.repository.HotelRepository;
import com.tw.hotel.views.BookingView;
import com.tw.hotel.views.HotelView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    public HotelService(HotelRepository hotelRepository, BookingRepository bookingRepository) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
    }

    public  BookingView bookHotel(String roomId, Integer rooms, String userId) {
        Booking savedBooking = bookingRepository.save(new Booking(null, userId, roomId, rooms));
        return savedBooking.view();
    }

    public List<HotelView> getHotelsInCity(String city) {
        return hotelRepository.findHotelsByCity(city).stream().map(Hotel::view).toList();
    }
    
    
}
