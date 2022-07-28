package com.triple.itinerary.domain.itinerary.service;

import com.triple.itinerary.domain.flight.entity.Flight;
import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import com.triple.itinerary.domain.place.entity.Place;
import com.triple.itinerary.domain.place.entity.PlaceType;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.UserTrip;
import com.triple.itinerary.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItineraryServiceTest {

    @Autowired
    ItineraryService itineraryService;

    @Autowired
    EntityManager em;

    Long tripId;
    Long placeId;
    Long flightId;

    @BeforeEach
    public void initIds() {

        Trip trip = Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .build();
        em.persist(trip);

        Place place = Place.builder()
                .name("장소이름")
                .location("위치")
                .placeType(PlaceType.SHOP)
                .build();
        em.persist(place);

        Flight flight = Flight.builder()
                .flightNumber("항공번호")
                .airline("항공사")
                .departureDate(LocalDate.of(2022, 07, 20))
                .departureTime(LocalTime.of(10, 10, 00))
                .arrivalTime(LocalTime.of(11, 10, 00))
                .departureAirport("출발공항")
                .arrivalAirport("도착공항")
                .build();

        em.persist(flight);

        this.tripId = trip.getId();
        this.placeId = place.getId();
        this.flightId = flight.getId();
    }

    @Test
    public void 일정_저장_항공타입_제목검증() throws Exception {
        // given
        Itinerary itinerary = Itinerary.builder()
                .itineraryType(ItineraryType.FLIGHT)
                .typeId(flightId)
                .visitDay(1)
                .build();

        // when
        Long itineraryId = itineraryService.add(tripId, itinerary);

        em.flush();
        em.clear();

        itinerary = em.find(Itinerary.class, itineraryId);

        // then
        assertThat(itinerary.getTitle()).isEqualTo("출발공항 10:10 - 도착공항 11:10");
        assertThat(itinerary.getSubTitle()).isEqualTo("항공사 · 항공번호");
    }

    @Test
    public void 일정_저장_장소타입_제목검증() throws Exception {
        // given
        Itinerary itinerary = Itinerary.builder()
                .itineraryType(ItineraryType.PLACE)
                .typeId(placeId)
                .visitDay(1)
                .build();

        // when
        Long itineraryId = itineraryService.add(tripId, itinerary);

        em.flush();
        em.clear();

        itinerary = em.find(Itinerary.class, itineraryId);

        // then
        assertThat(itinerary.getTitle()).isEqualTo("장소이름");
        assertThat(itinerary.getSubTitle()).isEqualTo(PlaceType.SHOP.getKorName() + " · 위치");
    }

    @Test
    public void 일정_저장_순서검증() throws Exception {
        // given
        Itinerary flightItinerary = Itinerary.builder()
                .itineraryType(ItineraryType.FLIGHT)
                .typeId(flightId)
                .visitDay(1)
                .build();

        Itinerary placeItinerary1 = Itinerary.builder()
                .itineraryType(ItineraryType.PLACE)
                .typeId(placeId)
                .visitDay(1)
                .build();

        Itinerary placeItinerary2 = Itinerary.builder()
                .itineraryType(ItineraryType.PLACE)
                .typeId(placeId)
                .visitDay(1)
                .build();

        // when
        Long flightItineraryId = itineraryService.add(tripId, flightItinerary);
        Long placeItineraryId1 = itineraryService.add(tripId, placeItinerary1);
        Long placeItineraryId2 = itineraryService.add(tripId, placeItinerary2);

        em.flush();
        em.clear();

        flightItinerary = em.find(Itinerary.class, flightItineraryId);
        placeItinerary1 = em.find(Itinerary.class, placeItineraryId1);
        placeItinerary2 = em.find(Itinerary.class, placeItineraryId2);

        // then
        assertThat(flightItinerary.getArrangeOrder()).isEqualTo(1);
        assertThat(placeItinerary1.getArrangeOrder()).isEqualTo(2);
        assertThat(placeItinerary2.getArrangeOrder()).isEqualTo(3);

        assertThat(flightItinerary.getVisitOrder()).isEqualTo(0);
        assertThat(placeItinerary1.getVisitOrder()).isEqualTo(1);
        assertThat(placeItinerary2.getVisitOrder()).isEqualTo(2);
    }
}