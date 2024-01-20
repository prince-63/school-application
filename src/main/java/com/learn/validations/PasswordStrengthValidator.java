package com.learn.validations;

import java.util.Arrays;
import java.util.List;

import com.learn.annotation.PasswordValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String>{
    List<String> weekPasswords;

    @Override
    public void initialize(PasswordValidator passwordValidator) {
        weekPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid(String passwordFields, ConstraintValidatorContext context) {
        return passwordFields != null && (!weekPasswords.contains(passwordFields));
    }
}
