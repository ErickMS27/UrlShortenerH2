package com.urlshortenerh2.repository;

import com.urlshortenerh2.dto.UrlDetailDTO;
import com.urlshortenerh2.model.UrlShortener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortener,Long> {

    public UrlShortener findByShortLink(String shortLink);
    public UrlShortener findByLongLink(String longLink);
    public List<UrlShortener> findTop10ByOrderByVisitCountDesc();
    public Page<UrlDetailDTO> findAllProjectBy(Pageable page);
}