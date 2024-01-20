package com.learn.validations;

import org.springframework.beans.BeanWrapperImpl;

import com.learn.annotation.FieldValueMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldValueMatchValidator implements ConstraintValidator<FieldValueMatch, Object>{
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldValueMatch fieldValueMatch) {
        this.field = fieldValueMatch.field();
        this.fieldMatch = fieldValueMatch.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object filedValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        if (filedValue != null) {
            return filedValue.equals(fieldMatchValue);
        }
        else {
            return fieldMatchValue == null;
        }
    }
}
