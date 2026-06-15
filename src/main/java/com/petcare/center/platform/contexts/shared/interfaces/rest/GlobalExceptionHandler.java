package com.petcare.center.platform.contexts.shared.interfaces.rest;

import com.petcare.center.platform.contexts.shared.domain.exceptions.BusinessRuleViolationException;
import com.petcare.center.platform.contexts.shared.domain.exceptions.ResourceNotFoundException;
import com.petcare.center.platform.contexts.shared.interfaces.rest.resources.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResource> handleNotFound(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResource("RESOURCE_NOT_FOUND", exception.getMessage(), null));
    }

    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ErrorResource> handleBusinessRule(BusinessRuleViolationException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResource("BUSINESS_RULE_VIOLATION", exception.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleValidation(MethodArgumentNotValidException exception) {
        var detail = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> "Field " + error.getField() + ": " + error.getDefaultMessage())
                .orElse("Validation failed");
        return ResponseEntity.badRequest().body(new ErrorResource("VALIDATION_ERROR", "Validation failed", detail));
    }
}
