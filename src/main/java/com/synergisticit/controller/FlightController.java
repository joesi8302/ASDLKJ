package com.synergisticit.controller;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.AirportRepository;
import com.synergisticit.repository.FlightRepository;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirlineService airlineService;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    AirportService airportService;



    @RequestMapping("flightForm")
    public String flightForm(Model model) {
        System.out.println("Flight Page Reached");
        Flight newFlight = new Flight();
        getModel(model);
        Long maxId = flightRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }

        newFlight.setFlightId(maxId);
        model.addAttribute("flight", newFlight);

        return "flightForm";
    }

    @RequestMapping("saveFlight")
    public String saveFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult result, Model model) {
        if(result.hasErrors()){
            getModel(model);
            return "flightForm";
        }

        Flight foundFlight = flightService.findById(flight.getFlightId());
        if(foundFlight != null) {
            flight.setCreatedDate(foundFlight.getCreatedDate());
            flight.setCreatedBy(foundFlight.getCreatedBy());
        }
        flightService.save(flight);

        Airport airport = airportRepository.getAirportByCity(flight.getArrivalCity());
        List<Flight> airportArrivalFlights = airport.getArrivalFlights();
        airportArrivalFlights.add(flight);
        airportService.save(airport);

        Airport airport2 = airportRepository.getAirportByCity(flight.getDepartureCity());
        Set<Flight> airportDepartureFlights = airport2.getDepartureFlights();
        airportDepartureFlights.add(flight);
        airportService.save(airport2);

        Airlines airline = airlineService.findById(flight.getOperatingAirlines().getAirlinesId());
        List<Flight> flights = airline.getFlights();
        flights.add(flight);
        airlineService.save(airline);

        return "redirect:flightForm";
    }

    @GetMapping("updateFlight")
    public String updateAccount(Flight flight, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "flightForm";
        }
        getModel(model);
        Flight foundFlight = flightService.findById(flight.getFlightId());
        model.addAttribute("account", foundFlight);



        return "flightForm";
    }


    @GetMapping("searchFlight")
    public String searchFlight(Flight flight, Model model, Principal principal){


        if(flight.getArrivalCity() == null || flight.getArrivalCity().isEmpty()){
            flight.setArrivalCity(null);
        }
        if(flight.getDepartureCity() == null || flight.getDepartureCity().isEmpty()){
            flight.setDepartureCity(null);
        }

        List<Flight> flights = flightRepository.findFlights(flight.getDepartureCity(),flight.getArrivalCity(),flight.getOperatingAirlines());



        model.addAttribute("flights", flights);

        List<Airlines> airlines = airlineService.findAll();
        model.addAttribute("airlines", airlines);

        List<String> airportCities = airportRepository.getAirportCities();
        model.addAttribute("airportCities", airportCities);


        return "flightSearch";
    }


    @GetMapping("deleteFlight")
    public String deleteFlight(Flight flight, BindingResult result, Model model){
        if(result.hasErrors()){
            return "flightForm";
        }
        flightService.delete(flight.getFlightId());
        getModel(model);
        return "redirect:flightForm";
    }




    public Model getModel(Model model) {
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);

        List<Airlines> airlines = airlineService.findAll();
        model.addAttribute("airlines", airlines);

        List<String> airportCities = airportRepository.getAirportCities();
        model.addAttribute("airportCities", airportCities);


        return model;
    }
}
