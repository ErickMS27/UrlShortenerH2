package com.urlshortenerh2.service;

import com.urlshortenerh2.dto.UrlDetailDTO;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.model.UrlShortener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<UrlShortener> getTop10VisitedLinks();
    public Long increaseAccessedViews(UrlShortenerRequestDTO urlShortenerRequestDTO);
    public Long countMostAccessedViews(UrlShortenerRequestDTO urlShortenerRequestDTO);
    public UrlShortener getEncodedUrl(String urlShortener);
    public void deleteShortLink(UrlShortener urlShortener);
    public UrlDetailDTO detailUrlForId(Long id);
    public Page<UrlDetailDTO> listPage(Pageable pages);
    public void deleteUrlForId(Long id);
    public UrlShortener updateLongLink(Long id, String longLink);
}