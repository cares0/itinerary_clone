package com.triple.itinerary.web.trip.query;

import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.UserTrip;
import com.triple.itinerary.domain.user.User;
import com.triple.itinerary.web.trip.response.TripDetailResponse;
import com.triple.itinerary.web.trip.response.TripListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TripQueryServiceTest {

    @Autowired
    TripQueryService tripQueryService;

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
    public void 여행_조회_단건() throws Exception {
        // given
        Trip trip = Trip.builder()
                .city("도시")
                .title("도시 여행")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .build();
        em.persist(trip);

        // when
        TripDetailResponse tripDetailResponse = tripQueryService.getOne(trip.getId());

        // then
        assertThat(tripDetailResponse.getId()).isEqualTo(trip.getId());
        assertThat(tripDetailResponse.getTitle()).isEqualTo(trip.getTitle());
        assertThat(tripDetailResponse.getDepartureDate()).isEqualTo(trip.getPeriod().getDepartureDate());
        assertThat(tripDetailResponse.getArrivalDate()).isEqualTo(trip.getPeriod().getArrivalDate());
    }

    @Test
    public void 여행_조회_리스트() throws Exception {
        // given
        Trip trip1 = Trip.builder()
                .city("도시1")
                .title("도시 여행1")
                .departureDate(LocalDate.of(2022, 07, 10))
                .arrivalDate(LocalDate.of(2022, 07, 20))
                .build();
        em.persist(trip1);

        Trip trip2 = Trip.builder()
                .city("도시1")
                .title("도시 여행1")
                .departureDate(LocalDate.of(2022, 07, 20))
                .arrivalDate(LocalDate.of(2022, 07, 30))
                .build();
        em.persist(trip2);

        User user = em.find(User.class, userId);
        UserTrip userTrip1 = UserTrip.builder()
                .user(user)
                .trip(trip1)
                .build();
        em.persist(userTrip1);

        UserTrip userTrip2 = UserTrip.builder()
                .user(user)
                .trip(trip2)
                .build();
        em.persist(userTrip2);

        // when
        List<TripListResponse> contents = tripQueryService.getList(userId);

        // then
        assertThat(contents.size()).isEqualTo(2);
    }

}