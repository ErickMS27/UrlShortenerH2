package com.urlshortenerh2.urlshortenerh2.service;

import com.urlshortenerh2.controller.UrlShortenerController;
import com.urlshortenerh2.exception.UrlErrorResponseDTO;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.dto.UrlShortenerResponseDTO;
import com.urlshortenerh2.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UrlGenerateShortLinkTest {

    @Mock
    private UrlShortenerService urlShortenerService;

    @InjectMocks
    private UrlShortenerController urlShortenerController;

    @Test
    void testGenerateShortLink_Success() {
        when(urlShortenerService.generateShortLink(any())).thenReturn("https://short.url/441a1c77");
        when(urlShortenerService.increaseAccessedViews(any())).thenReturn(1L);

        UrlShortenerRequestDTO requestDTO = new UrlShortenerRequestDTO();
        requestDTO.setLongLink("https://www.youtube.com/");

        ResponseEntity<UrlShortenerResponseDTO> responseEntity = urlShortenerController.generateShortLink(requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        UrlShortenerResponseDTO responseDTO = responseEntity.getBody();
        assertEquals("https://www.youtube.com/", responseDTO.getLongLink());
        assertEquals("https://short.url/441a1c77", responseDTO.getShortLink());
        assertEquals(1L, responseDTO.getVisitCount());
    }

    @Test
    void testGenerateShortLink_Failure() {

        when(urlShortenerService.generateShortLink(any())).thenReturn("");

        UrlShortenerRequestDTO requestDTO = new UrlShortenerRequestDTO();
        requestDTO.setLongLink("https://www.youtube.com/");

        ResponseEntity<UrlShortenerResponseDTO> responseEntity = urlShortenerController.generateShortLink(requestDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof UrlShortenerResponseDTO);
        UrlShortenerResponseDTO responseDTO = responseEntity.getBody();
        }
    }
