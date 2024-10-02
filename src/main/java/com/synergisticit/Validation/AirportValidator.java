package com.synergisticit.Validation;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AirportValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return Airport.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Airport airport = (Airport) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportName",
                "airportName.required",
                "Airport Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "airportCode","airportCode.required",
                "Airport Code is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "airportCity","airportCity.required",
                "Airport City is required");

    }
}
