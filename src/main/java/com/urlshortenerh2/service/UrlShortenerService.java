package com.urlshortenerh2.service;

import com.urlshortenerh2.model.LinkCounts;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface UrlShortenerService {
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    public String generateShortLink(UrlShortenerRequestDTO urlShortenerRequestDTO);
    public UrlShortener persistShortLink(UrlShortener urlShortener);
    public UrlShortener getEncodedUrl(String urlShortener);
    public void deleteShortLink(UrlShortener urlShortener);
    public List<LinkCounts> getTop10VisitedLinks(Integer linkCounts);
}