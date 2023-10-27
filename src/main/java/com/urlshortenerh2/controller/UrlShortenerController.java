package com.urlshortenerh2.controller;

import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.dto.UrlShortenerDTO;
import com.urlshortenerh2.dto.UrlErrorResponseDTO;
import com.urlshortenerh2.dto.UrlResponseDTO;
import com.urlshortenerh2.service.UrlShortenerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class UrlShortenerController
{
    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlShortenerDTO urlShortenerDTO)
    {
        UrlShortener urlToRet = urlShortenerService.generateShortLink(urlShortenerDTO);

        if(urlToRet != null)
        {
            UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
            urlResponseDTO.setOriginalUrl(urlToRet.getOriginalUrl());
            urlResponseDTO.setExpirationDate(urlToRet.getExpirationDate());
            urlResponseDTO.setShortLink(urlToRet.getShortLink());
            return new ResponseEntity<UrlResponseDTO>(urlResponseDTO, HttpStatus.OK);
        }

        UrlErrorResponseDTO urlErrorResponseDTO= new UrlErrorResponseDTO();
        urlErrorResponseDTO.setStatus("404");
        urlErrorResponseDTO.setError("There was an error processing your request. please try again.");
        return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDTO,HttpStatus.OK);

    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(shortLink))
        {
            UrlErrorResponseDTO urlErrorResponseDto = new UrlErrorResponseDTO();
            urlErrorResponseDto.setError("Invalid Url");
            urlErrorResponseDto.setStatus("400");
            return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDto,HttpStatus.OK);
        }
        UrlShortener urlToRet = urlShortenerService.getEncodedUrl(shortLink);

        if(urlToRet == null)
        {
            UrlErrorResponseDTO urlErrorResponseDTO = new UrlErrorResponseDTO();
            urlErrorResponseDTO.setError("Url does not exist or it might have expired!");
            urlErrorResponseDTO.setStatus("400");
            return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDTO,HttpStatus.OK);
        }

        if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now()))
        {
            urlShortenerService.deleteShortLink(urlToRet);
            UrlErrorResponseDTO urlErrorResponseDto = new UrlErrorResponseDTO();
            urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
            urlErrorResponseDto.setStatus("200");
            return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDto,HttpStatus.OK);
        }

        response.sendRedirect(urlToRet.getOriginalUrl());
        return null;
    }
}