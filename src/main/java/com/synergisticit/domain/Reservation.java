package com.synergisticit.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservation")
    @JsonManagedReference
    private Passenger passenger;

    @ManyToOne
    private Flight flight;

    private int checkedBags;

    private boolean checkedIn;
}
