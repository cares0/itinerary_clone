package com.triple.itinerary.domain.trip.service;

import com.triple.itinerary.domain.trip.entity.Partner;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.TripStyle;
import com.triple.itinerary.domain.trip.exception.WrongDepartureDateException;
import com.triple.itinerary.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    Long userId;

    @BeforeEach
    public void initUserId() {
        User user = User.builder().build();
        em.persist(user);
        userId = user.getId();
    }

    @Test
    public void 여행_저장_정상요청() throws Exception {
        // given
        Trip trip = Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .build();

        // when
        Long findId = tripService.add(userId, trip);
        Trip findTrip = em.find(Trip.class, trip.getId());

        // then
        assertThat(findTrip).isEqualTo(trip);
    }

    @Test
    public void 여행_저장_날짜예외() throws Exception {
        assertThatThrownBy(() -> Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 20))
                .arrivalDate(LocalDate.of(2022, 07, 10))
                .build())
                .isInstanceOf(WrongDepartureDateException.class);
    }

    @Test
    public void 여행_수정_제목() throws Exception {
        // given
        Trip originalTrip = Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .build();

        Long originalId = tripService.add(userId, originalTrip);

        em.flush();
        em.clear();

        Trip modifyingTrip = Trip.builder()
                .title("제목 수정")
                .build();

        // when
        tripService.modify(originalId, modifyingTrip);

        em.flush();
        em.clear();

        Trip modifiedTrip = em.find(Trip.class, originalId);

        // then
        assertThat(originalTrip.getTitle()).isEqualTo("도시 여행");
        assertThat(modifiedTrip.getTitle()).isEqualTo("제목 수정");
    }

    @Test
    public void 여행_수정_날짜() throws Exception {
        // given
        Trip originalTrip = Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .build();

        Long originalId = tripService.add(userId, originalTrip);

        em.flush();
        em.clear();

        Trip modifyingTrip = Trip.builder()
                .departureDate(LocalDate.of(2022, 07, 20))
                .arrivalDate(LocalDate.of(2022, 07, 30))
                .build();

        // when
        tripService.modify(originalId, modifyingTrip);

        em.flush();
        em.clear();

        Trip modifiedTrip = em.find(Trip.class, originalId);

        // then
        assertThat(originalTrip.getPeriod().getDepartureDate()).isEqualTo(LocalDate.of(2022, 07, 10));
        assertThat(originalTrip.getPeriod().getArrivalDate()).isEqualTo(LocalDate.of(2022, 07, 20));
        assertThat(modifiedTrip.getPeriod().getDepartureDate()).isEqualTo(LocalDate.of(2022, 07, 20));
        assertThat(modifiedTrip.getPeriod().getArrivalDate()).isEqualTo(LocalDate.of(2022, 07, 30));
    }

    @Test
    public void 여행_수정_스타일() throws Exception {
        // given
        Trip originalTrip = Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .tripStyle(TripStyle.CULTURE)
                .partner(Partner.ETC)
                .build();

        Long originalId = tripService.add(userId, originalTrip);

        em.flush();
        em.clear();

        Trip modifyingTrip = Trip.builder()
                .city("도시 수정")
                .tripStyle(TripStyle.ACTIVITY)
                .partner(Partner.CHILD)
                .build();

        // when
        tripService.modify(originalId, modifyingTrip);

        em.flush();
        em.clear();

        Trip modifiedTrip = em.find(Trip.class, originalId);

        // then
        assertThat(originalTrip.getCity()).isEqualTo("도시");
        assertThat(originalTrip.getPartner()).isEqualTo(Partner.ETC);
        assertThat(originalTrip.getTripStyle()).isEqualTo(TripStyle.CULTURE);

        assertThat(modifiedTrip.getCity()).isEqualTo("도시 수정");
        assertThat(modifiedTrip.getPartner()).isEqualTo(Partner.CHILD);
        assertThat(modifiedTrip.getTripStyle()).isEqualTo(TripStyle.ACTIVITY);
    }

}