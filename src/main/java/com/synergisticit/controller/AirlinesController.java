package com.synergisticit.controller;

import com.synergisticit.Validation.AirlineValidator;
import com.synergisticit.Validation.UserValidator;
import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.AirlinesRepository;
import com.synergisticit.service.AirlineService;
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
public class AirlinesController {

    @Autowired
    AirlineService airlineService; //Which type of dependency injection is this: Field Injection

    @Autowired
    AirlinesRepository airlinesRepository;

    @Autowired
    AirlineValidator airlineValidator;

    @InitBinder
    public void initBinder (WebDataBinder binder){
        binder.addValidators(airlineValidator);
    }

    @RequestMapping("airlinesForm")
    public String airlinesForm(Model model) {
        System.out.println("Airlines Page Reached");
        Airlines newAirlines= new Airlines();
        getModel(model);
        Long maxId = airlinesRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }

        newAirlines.setAirlinesId(maxId);
        model.addAttribute("airline", newAirlines);

        return "airlinesForm";
    }

    @RequestMapping("saveAirlines")
    public String saveAirlines(@Valid @ModelAttribute("airline") Airlines airline, BindingResult result, Model model) {
        if(result.hasErrors()){
            getModel(model);
            return "airlinesForm";
        }

        Airlines foundAirline = airlineService.findById(airline.getAirlinesId());
        if(foundAirline != null) {
            airline.setCreatedDate(foundAirline.getCreatedDate());
            airline.setCreatedBy(foundAirline.getCreatedBy());
        }
        airlineService.save(airline);

        return "redirect:airlinesForm";
    }


    @GetMapping("deleteAirlines")
    public String deletePassenger(Airlines airline, BindingResult result, Model model){
        if(result.hasErrors()){
            return "airlinesForm";
        }
        airlineService.delete(airline.getAirlinesId());
        getModel(model);
        return "redirect:airlinesForm";
    }

    @GetMapping("updateAirlines")
    public String updateAccount(Airlines airline, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "airlinesForm";
        }
        getModel(model);
        Airlines foundAirline = airlineService.findById(airline.getAirlinesId());
        model.addAttribute("account", foundAirline);


        return "airlinesForm";
    }




    public Model getModel(Model model) {
        List<Airlines> airlines = airlineService.findAll();
        model.addAttribute("airlines", airlines);


        return model;
    }
}
