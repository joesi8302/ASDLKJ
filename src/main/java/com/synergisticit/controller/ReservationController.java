package com.synergisticit.controller;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Reservation;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.repository.ReservationRepository;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.ReservationService;
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
public class ReservationController {


    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    FlightService flightService;

    @RequestMapping("reservationForm")
    public String reservationForm(Model model) {
        System.out.println("Reservation Page Reached");

        getModel(model);

        return "reservationForm";
    }

    @RequestMapping("saveReservation")
    public String saveReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult result, Model model) {
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            getModel(model);
            System.out.println(reservation.isCheckedIn());
            return "reservationForm";
        }

        System.out.println(reservation.isCheckedIn());



        Reservation foundReservation = reservationService.findById(reservation.getTicketNumber());
        if(foundReservation != null) {
            reservation.setCreatedDate(foundReservation.getCreatedDate());
            reservation.setCreatedBy(foundReservation.getCreatedBy());

        }

        reservationService.save(reservation);

        return "redirect:reservationForm";
    }

    @GetMapping("updateReservation")
    public String updateReservation(Reservation reservation, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "reservationForm";
        }
        getModel(model);
        Reservation foundReservation= reservationService.findById(reservation.getTicketNumber());
        model.addAttribute("reservation", foundReservation);



        return "reservationForm";
    }

    @GetMapping("checkInReservation")
    public String checkInReservation(Reservation reservation, BindingResult result, Model model, Principal principal){

        Reservation foundReservation = reservationService.findById(reservation.getTicketNumber());
        if(foundReservation != null) {
            reservation.setCreatedDate(foundReservation.getCreatedDate());
            reservation.setCreatedBy(foundReservation.getCreatedBy());

        }
        foundReservation.setCheckedIn(true);

        reservationService.save(foundReservation);

        return "redirect:reservationForm";
    }


    @GetMapping("deleteReservation")
    public String deleteReservation(Reservation reservation, BindingResult result, Model model){
        if(result.hasErrors()){
            return "reservationForm";
        }
        reservationService.delete(reservation.getTicketNumber());
        getModel(model);
        return "redirect:reservationForm";
    }




    public Model getModel(Model model) {
        Reservation newReservation = new Reservation();
        Long maxId = reservationRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }

        newReservation.setTicketNumber(maxId);
        model.addAttribute("reservation", newReservation);


        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations", reservations);

        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights", flights);


        return model;
    }
}
