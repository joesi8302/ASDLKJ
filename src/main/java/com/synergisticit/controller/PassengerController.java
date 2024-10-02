package com.synergisticit.controller;

import com.synergisticit.Validation.PassengerValidator;
import com.synergisticit.domain.*;
import com.synergisticit.repository.FlightRepository;
import com.synergisticit.repository.PassengerRepository;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PassengerController {


    @Autowired
    PassengerService passengerService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerValidator passengerValidator;

    @InitBinder
    public void initBinder (WebDataBinder binder){
        binder.addValidators(passengerValidator);
    }


    @RequestMapping("passengerForm")
    public String passengerForm(Model model) {
        System.out.println("Passenger Page Reached");
        Passenger newPassenger = new Passenger();
        getModel(model);

        Long maxId = passengerRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }
        newPassenger.setPassengerId(maxId);

        model.addAttribute("passenger", newPassenger);


        return "passengerForm";
    }

    @RequestMapping("savePassenger")
    public String savePassenger(@Valid @ModelAttribute("passenger") Passenger passenger, BindingResult result, Model model) {
        if(result.hasErrors()){
            getModel(model);
            return "passengerForm";
        }

        Passenger foundPassenger = passengerService.findById(passenger.getPassengerId());
        if(foundPassenger != null) {
            passenger.setCreatedDate(foundPassenger.getCreatedDate());
            passenger.setCreatedBy(foundPassenger.getCreatedBy());
        }
        passengerService.save(passenger);

        return "redirect:passengerForm";
    }


    @GetMapping("deletePassenger")
    public String deletePassenger(Passenger passenger, BindingResult result, Model model){
        if(result.hasErrors()){
            return "passengerForm";
        }
        passengerService.delete(passenger.getPassengerId());
        getModel(model);
        return "redirect:passengerForm";
    }

    @GetMapping("updatePassenger")
    public String updateAccount(Passenger passenger, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "passengerForm";
        }

        Passenger foundPassenger= passengerService.findById(passenger.getPassengerId());
        model.addAttribute("account", foundPassenger);



        return "passengerForm";
    }


    @GetMapping("bookPassenger")
    public String bookPassenger(Passenger passenger, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "passengerForm";
        }

        Long maxId = passengerRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }
        passenger.setPassengerId(maxId);

        getModel(model);


        Flight flight = flightService.findById(passenger.getReservation().getFlight().getFlightId());

        passenger.getReservation().setFlight(flight);



        return "passengerForm";
    }




    public Model getModel(Model model) {


        List<Passenger> passengers = passengerService.findAll();
        model.addAttribute("passengers", passengers);

        List<Gender> genders = List.of(Gender.values());

        model.addAttribute("genders", genders);

        List<IdentificationType> identificationTypes = List.of(IdentificationType.values());

        model.addAttribute("idTypes", identificationTypes);

        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);

        return model;
    }
}
