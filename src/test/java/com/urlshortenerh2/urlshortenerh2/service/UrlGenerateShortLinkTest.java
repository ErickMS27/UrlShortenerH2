package com.urlshortenerh2.urlshortenerh2.service;

import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UrlGenerateShortLinkTest {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Test
    void testGenerateShortLink(){
        UrlShortenerRequestDTO requestDTO = new UrlShortenerRequestDTO();
        requestDTO.setLongLink("https://www.youtube.com/");

        String shortLink = urlShortenerService.generateShortLink(requestDTO);

        assertNotNull(shortLink);
        assertFalse(shortLink.isEmpty());
    }
}
