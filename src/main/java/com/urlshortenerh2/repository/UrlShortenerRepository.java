package com.urlshortenerh2.repository;

import com.urlshortenerh2.model.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortener,Long> {

    public UrlShortener findByShortLink(String shortLink);

}