package com.synergisticit.Validation;

import com.synergisticit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","username.required","Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userPassword","password.required","Password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userEmail","email.required","Email is required");

    }
}
