package com.urlshortenerh2.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
public class ValidateErrorData {

    String field;
    String message;

    public ValidateErrorData(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }

    public ValidateErrorData(String field, String defaultMessage) {
        this.field = field;
        this.message = defaultMessage;
    }
}

    