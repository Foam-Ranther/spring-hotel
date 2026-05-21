package com.tw.hotel.controllers;

import com.tw.hotel.service.HotelService;
import com.tw.hotel.views.BookHotelRequest;
import com.tw.hotel.views.BookingView;
import com.tw.hotel.views.HotelView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class HotelController {
    private final HotelService hotelService;
    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search/hotels")
    public ResponseEntity<List<HotelView>> getHotels(@RequestParam String city) {
        logger.info("Received request for hotels in {}", city);
        return ResponseEntity.ok(hotelService.getHotelsInCity(city));
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingView> bookHotel(@RequestBody BookHotelRequest bookHotelRequest){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        BookingView bookingView = hotelService.bookHotel(bookHotelRequest.hotel_id(), bookHotelRequest.rooms(), authentication.getName());
        return ResponseEntity.ok(bookingView);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingView>> listBookings(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        List<BookingView> bookings = hotelService.listBookings(authentication.getName());
        return  ResponseEntity.ok(bookings);
    }

}


