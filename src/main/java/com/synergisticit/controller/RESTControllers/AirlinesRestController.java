package com.synergisticit.controller.RESTControllers;

import com.synergisticit.domain.Airlines;
import com.synergisticit.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlinesRestController {

    @Autowired
    private AirlineService airlinesService;


    @PostMapping
    public ResponseEntity<Airlines> createAirlines(@RequestBody Airlines airlines) {
        Airlines savedAirlines = airlinesService.save(airlines);
        return ResponseEntity.ok(savedAirlines);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Airlines> getAirlinesById(@PathVariable Long id) {
        Airlines airlines = airlinesService.findById(id);
        if (airlines != null) {
            return ResponseEntity.ok(airlines);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Airlines>> getAllAirlines() {
        List<Airlines> airlinesList = airlinesService.findAll();
        return ResponseEntity.ok(airlinesList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Airlines> updateAirlines(@PathVariable Long id, @RequestBody Airlines airlines) {
        Airlines existingAirlines = airlinesService.findById(id);
        if (existingAirlines != null) {
            airlines.setAirlinesId(id); // Ensure the ID is correct
            Airlines updatedAirlines = airlinesService.save(airlines);
            return ResponseEntity.ok(updatedAirlines);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an airline
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirlines(@PathVariable Long id) {
        Airlines existingAirlines = airlinesService.findById(id);
        if (existingAirlines != null) {
            airlinesService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
