package com.synergisticit.controller;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.AirportRepository;
import com.synergisticit.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AirportController {


    @Autowired
    AirportService airportService;

    @Autowired
    AirportRepository airportRepository;

    @RequestMapping("airportForm")
    public String airportForm(Model model) {
        System.out.println("Airport Page Reached");
        Airport newAirport = new Airport();
        getModel(model);
        Long maxId = airportRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }

        newAirport.setAirportId(maxId);
        model.addAttribute("airport", newAirport);

        return "airportForm";
    }

    @RequestMapping("saveAirport")
    public String saveAirport(@Valid @ModelAttribute("airport") Airport airport, BindingResult result, Model model) {
        if(result.hasErrors()){
            getModel(model);
            return "airportForm";
        }

        Airport foundAirport = airportService.findById(airport.getAirportId());
        if(foundAirport != null) {
            airport.setCreatedDate(foundAirport.getCreatedDate());
            airport.setCreatedBy(foundAirport.getCreatedBy());
        }
        airportService.save(airport);

        return "redirect:airportForm";
    }

    @GetMapping("updateAirport")
    public String updateAccount(Airport airport, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "airportForm";
        }
        getModel(model);
        Airport foundAirport = airportService.findById(airport.getAirportId());
        model.addAttribute("account", foundAirport);



        return "airportForm";
    }


    @GetMapping("deleteAirport")
    public String deleteAirport(Airport airport, BindingResult result, Model model){
        if(result.hasErrors()){
            return "airportForm";
        }
        airportService.delete(airport.getAirportId());
        getModel(model);
        return "redirect:airportForm";
    }




    public Model getModel(Model model) {
        List<Airport> airports = airportService.findAll();
        model.addAttribute("airports", airports);


        return model;
    }
}
