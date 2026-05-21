package com.tw.hotel.models;

import com.tw.hotel.views.HotelView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Hotel {
    @Id
    private final String id;
    private final String name;
    @Field("rooms_available")
    private final int roomsAvailable;
    private final String city;

    public Hotel(String id, String name, int roomsAvailable, String city) {
        this.id = id;
        this.name = name;
        this.roomsAvailable = roomsAvailable;
        this.city = city;
    }

    public HotelView view() {
        return new HotelView(id, name, roomsAvailable, city);
    }

}
