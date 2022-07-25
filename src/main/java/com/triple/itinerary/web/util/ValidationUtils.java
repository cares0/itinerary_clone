package com.triple.itinerary.web.util;

import com.triple.itinerary.web.exception.ValidationFailException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ValidationUtils {

    public static void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            throw new ValidationFailException(errorMap.toString());
        }
    }
}
