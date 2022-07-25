package com.triple.itinerary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ItineraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItineraryApplication.class, args);
    }

}
