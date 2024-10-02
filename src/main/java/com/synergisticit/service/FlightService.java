package com.synergisticit.service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Flight;

import java.util.List;

public interface FlightService {

    Flight save(Flight flight);

    List<Flight> findAll();

    Flight findById(Long id);

    void delete(Long id);
}
