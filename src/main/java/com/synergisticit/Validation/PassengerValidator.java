package com.synergisticit.Validation;

import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PassengerValidator implements Validator {

    @Autowired
    PassengerService passengerService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Passenger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Passenger passenger = (Passenger) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","firstName.required","First Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "lastName",
                "lastName.required",
                "Last Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "email",
                "email.required",
                "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "phone",
                "phone.required",
                "Phone Number is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "dateOfBirth",
                "dateOfBirth.required",
                "Date of birth is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "idType",
                "idType.required",
                "Identification Type is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "gender",
                "gender.required",
                "Gender is required");

        if(passenger.getDateOfBirth() != null && passenger.getDateOfBirth().isAfter(LocalDate.now())){
            errors.rejectValue("dateOfBirth",
                    "dateOfBirth.value",
                    "Must be a valid Date of Birth");
        }
    }
}
