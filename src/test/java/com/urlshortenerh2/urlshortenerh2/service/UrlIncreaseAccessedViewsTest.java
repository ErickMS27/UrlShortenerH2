package com.urlshortenerh2.urlshortenerh2.service;

import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

 class UrlIncreaseAccessedViewsTest {

     @Autowired
     private UrlShortenerService urlShortenerService;

     @Test
     void testIncreaseAccessedViews(){
         UrlShortenerRequestDTO requestDTO = new UrlShortenerRequestDTO();
         requestDTO.setLongLink("https://www.youtube.com/");

         String shortLink = urlShortenerService.generateShortLink(requestDTO);
         Long viewCountBefore = urlShortenerService.increaseAccessedViews(requestDTO);
         Long viewCountAfter = urlShortenerService.increaseAccessedViews(requestDTO);

         assertNotNull(viewCountBefore);
         assertNotNull(viewCountAfter);
         assertTrue(viewCountAfter > viewCountBefore);
     }

}
