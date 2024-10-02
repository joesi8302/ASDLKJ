package com.synergisticit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Flight extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String flightNumber;

    @ManyToOne
    @JsonIgnore
    private Airlines operatingAirlines;

    private String departureCity;

    private String arrivalCity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrivalDateTime;

    private double ticketPrice;

    @OneToMany
    private List<Reservation> reservations = new ArrayList<>(); //cannot have more than the capacity;

    private int capacity;  // for searching flights page, make sure if fully booked it says it is fully booked, other than that we still are able
                            // to book a flight

}
