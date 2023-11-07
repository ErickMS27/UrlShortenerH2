package com.urlshortenerh2.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlErrorResponseDTO {
    private String status;
    private String error;

}