package com.urlshortenerh2.service;

import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.dto.UrlShortenerDTO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Service
public interface UrlShortenerService {
    public @NotNull(message = "{field.notnull}") @NotEmpty(message = "{field.notempty}") String generateShortLink(String longLink, UrlShortenerDTO urlShortenerDTO);
    public UrlShortener persistShortLink(UrlShortener urlShortener);
    public UrlShortener getEncodedUrl(String urlShortener);
    public void deleteShortLink(UrlShortener urlShortener);
}