package com.tw.hotel.controllers;

import com.tw.hotel.service.HotelService;
import com.tw.hotel.views.HotelView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class HotelController {
    private final HotelService hotelService;
    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("api/search/hotels")
    public ResponseEntity<List<HotelView>> getHotels(@RequestParam String city) {
        logger.info("Received request for hotels in {}", city);
        return ResponseEntity.ok(hotelService.getHotelsInCity(city));
    }

}
