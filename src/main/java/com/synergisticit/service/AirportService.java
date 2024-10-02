package com.synergisticit.service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Airport;

import java.util.List;

public interface AirportService {

    Airport save(Airport airport);

    List<Airport> findAll();

    Airport findById(Long id);

    void delete(Long id);
}
