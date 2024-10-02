package com.synergisticit.controller.RESTControllers;

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportRESTController {

    @Autowired
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<Airport> saveAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.save(airport);
        return new ResponseEntity<>(savedAirport, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.findById(id);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.findAll();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
