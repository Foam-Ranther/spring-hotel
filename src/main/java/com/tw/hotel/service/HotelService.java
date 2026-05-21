package com.tw.hotel.service;

import com.tw.hotel.models.Hotel;
import com.tw.hotel.repository.HotelRepository;
import com.tw.hotel.views.HotelView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelView> getHotelsInCity(String city) {
        return hotelRepository.findHotelsByCity(city).stream().map(Hotel::view).toList();
    }
}
