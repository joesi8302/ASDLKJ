package com.synergisticit.Validation;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FlightValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return Flight.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "flightNumber",
                "flightNumber.required",
                "Flight Number is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "operatingAirlines",
                "operatingAirlines.required",
                "Airline is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "departureCity",
                "departureCity.required",
                "Departure City is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "arrivalCity",
                "arrivalCity.required",
                "Arrival City is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "departureDateTime",
                "departureDateTime.required",
                "Departure Time is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "arrivalDateTime",
                "arrivalDateTime.required",
                "Arrival Time is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "ticketPrice",
                "ticketPrice.required",
                "Ticket Price is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "capacity",
                "capacity.required",
                "Capacity is required");

    }
}

