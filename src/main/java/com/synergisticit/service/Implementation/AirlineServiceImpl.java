package com.synergisticit.service.Implementation;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Airport;
import com.synergisticit.repository.AirlinesRepository;
import com.synergisticit.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlinesRepository airlinesRepository;

    @Override
    public Airlines save(Airlines airlines) {
        return airlinesRepository.save(airlines);
    }

    @Override
    public List<Airlines> findAll() {
        return airlinesRepository.findAll();
    }

    @Override
    public Airlines findById(Long id) {

        return airlinesRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id)  {
        Optional<Airlines> optional = airlinesRepository.findById(id);
        optional.ifPresent(airlines -> airlinesRepository.delete(airlines));
    }

    @Override
    public Airlines update(Airlines airlines) {
        if (airlines != null && airlines.getAirlinesId() != null) {
            Optional<Airlines> existingAirlines = airlinesRepository.findById(airlines.getAirlinesId());
            if (existingAirlines.isPresent()) {
                return airlinesRepository.save(airlines); // This performs the update
            }
        }
        return null; // or throw an exception indicating the entity does not exist
    }

    @Override
    public Airlines findByAirlinesName(String airlineName) {
        return airlinesRepository.findByAirlineName(airlineName);
    }
}
