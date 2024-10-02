package com.synergisticit.service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    Reservation findById(Long id);

    void delete(Long id);
}
