package com.urlshortenerh2.service;

import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.dto.UrlShortenerDTO;
import org.springframework.stereotype.Service;

@Service
public interface UrlShortenerService {
    public UrlShortener generateShortLink(UrlShortenerDTO urlShortenerDTO);
    public UrlShortener persistShortLink(UrlShortener urlShortener);
    public UrlShortener getEncodedUrl(String urlShortener);
    public void deleteShortLink(UrlShortener urlShortener);
}
