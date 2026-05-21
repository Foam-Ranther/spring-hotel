package com.tw.hotel.controllers;


import com.tw.hotel.repository.HotelRepository;
import com.tw.hotel.views.HotelView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureRestTestClient
public class HotelControllerTest {
    @Autowired
    private RestTestClient client;

    @Autowired
    private HotelRepository hotelRepository;

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
}
