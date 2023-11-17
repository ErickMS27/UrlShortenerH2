package com.urlshortenerh2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UrlErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidateErrorData>> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<ValidateErrorData> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(ValidateErrorData::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

}


