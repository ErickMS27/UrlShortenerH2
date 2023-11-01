package com.urlshortenerh2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UrlShortenerResponseDTO {

    private String longLink;
    private String shortLink;
    private LocalDateTime estimatedTime = LocalDateTime.now();

}