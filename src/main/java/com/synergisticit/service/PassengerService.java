package com.synergisticit.service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger save(Passenger passenger);

    List<Passenger> findAll();

    Passenger findById(Long id);

    void delete(Long id);
}
