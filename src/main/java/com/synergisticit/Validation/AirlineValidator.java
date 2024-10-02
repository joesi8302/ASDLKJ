package com.synergisticit.Validation;

import com.synergisticit.domain.Airlines;
import com.synergisticit.domain.User;
import com.synergisticit.repository.AirlinesRepository;
import com.synergisticit.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AirlineValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Autowired
    AirlineService airlineService;

    @Autowired
    AirlinesRepository airlinesRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Airlines.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Airlines airline = (Airlines) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airlinesCode","airlinesCode.required","Airline Code is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airlinesName","airlinesName.required","Airline Name is required");

        Airlines foundAirlineName = airlineService.findByAirlinesName(airline.getAirlinesName());

        Airlines foundAirlineCode = airlinesRepository.findByAirlineCode(airline.getAirlinesCode());

        if(foundAirlineName != null){
            errors.rejectValue("airlinesName",
                    "airlinesName.value",
                    "Airline Name is already made");
        }

        if(foundAirlineCode != null){
            errors.rejectValue("airlinesCode",
                    "airlinesCode.value",
                    "Airline Code is already in use");
        }


    }
}

