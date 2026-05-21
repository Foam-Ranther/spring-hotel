package com.tw.hotel.models;

import com.tw.hotel.views.BookingView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Booking {
    @Id
    private final String bookingId;
    private final String userId;
    private final String roomId;
    private final int rooms;


    public Booking(String bookingId, String userId, String roomId, int rooms) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.rooms = rooms;
    }

    public BookingView view(){
        return new BookingView(bookingId, userId,roomId,rooms);
    }
}
