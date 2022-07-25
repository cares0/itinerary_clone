package com.triple.itinerary.domain.trip.service;

import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.exception.WrongDepartureDateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TripServiceTest {

    @Autowired
    TripService tripService;

    @Autowired
    EntityManager em;

    @Test
    public void 여행_저장_정상요청() throws Exception {
        // given
        Trip trip = createTrip("도시",
                LocalDate.of(2022, 07, 10),
                LocalDate.of(2022, 07, 20));

        // when
        Long findId = tripService.add(trip);
        Trip findTrip = em.find(Trip.class, trip.getId());

        // then
        assertThat(findTrip).isEqualTo(trip);
    }

    @Test
    public void 여행_저장_날짜예외() throws Exception {
        assertThatThrownBy(() -> createTrip("도시",
                LocalDate.of(2022, 07, 20),
                LocalDate.of(2022, 07, 10)))
                .isInstanceOf(WrongDepartureDateException.class);
    }



    private Trip createTrip(String city, LocalDate departureDate, LocalDate arrivalDate) {
        return Trip.builder()
                .city(city)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .build();
    }

}