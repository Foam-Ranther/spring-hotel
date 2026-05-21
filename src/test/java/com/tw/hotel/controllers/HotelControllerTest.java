package com.tw.hotel.controllers;


import com.tw.hotel.service.HotelService;
import com.tw.hotel.views.BookHotelRequest;
import com.tw.hotel.views.BookingView;
import com.tw.hotel.views.HotelView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureRestTestClient
public class HotelControllerTest {
    @Autowired
    private RestTestClient client;
    @Test
    void shouldListHotelsUponSearch() {

        List<HotelView> searchResults = client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/search/hotels").queryParam("city", "New York").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<HotelView>>() {
                })
                .returnResult().getResponseBody();

        List<HotelView> expected = new ArrayList<>();
        assertEquals(expected, searchResults);
    }

    @Test
    void shouldBookAHotel() {
        BookingView expectedBookingView = new BookingView("1", "1", "1", 20);
        HotelService hotelService = mock(HotelService.class);
        when(hotelService.bookHotel("1",10, "user1")).thenReturn(expectedBookingView);

        BookingView responseBody = client.post().
                uri("/api/bookings")
                .body(new BookHotelRequest("1", 10))
                .exchange()
                .expectBody(BookingView.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody.bookingId());
        assertEquals("1", responseBody.roomId());
        assertEquals(10, responseBody.rooms());
        assertEquals("user1", responseBody.userId());


    }
}
