package com.synergisticit.service;

import com.synergisticit.domain.Airlines;

import java.util.List;

public interface AirlineService {

    Airlines save(Airlines airlines);

    List<Airlines> findAll();

    Airlines findById(Long id);

    Airlines update(Airlines airlines);

    void delete(Long id);

    Airlines findByAirlinesName(String airlineName);
}
