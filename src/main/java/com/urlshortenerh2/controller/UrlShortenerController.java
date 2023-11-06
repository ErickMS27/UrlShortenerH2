package com.urlshortenerh2.controller;

import com.urlshortenerh2.dto.LinkCountsDTO;
import com.urlshortenerh2.dto.UrlErrorResponseDTO;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.dto.UrlShortenerResponseDTO;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key")
public class UrlShortenerController
{
    @Autowired
    private UrlShortenerService urlShortenerService;

//    public UrlShortenerController(UrlShortenerService urlShortenerService) {
//        this.urlShortenerService = urlShortenerService;
//    }

    @PostMapping
    public ResponseEntity<UrlShortenerResponseDTO> generateShortLink(@RequestBody UrlShortenerRequestDTO urlShortenerRequestDTO) {

        String longLink = urlShortenerRequestDTO.getLongLink();
        String shortLink = urlShortenerService.generateShortLink(urlShortenerRequestDTO);

        UrlShortenerResponseDTO responseDTO = new UrlShortenerResponseDTO();
        responseDTO.setLongLink(longLink);
        responseDTO.setShortLink(shortLink);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/top-10-visited")
    public ResponseEntity<List<UrlShortener>>getTop10VisitedLinks(@RequestBody LinkCountsDTO linkCountsDTO) {

        Integer top10VisitedLinks = linkCountsDTO.getLinkCounts();
        List<UrlShortener> topVisitedLinks = urlShortenerService.getTop10VisitedLinks(top10VisitedLinks);

        return ResponseEntity.ok(topVisitedLinks);
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectTolongLink(@PathVariable String shortLink, HttpServletResponse response)
            throws IOException {

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

        UrlShortenerResponseDTO urlShortenerResponseDTO = new UrlShortenerResponseDTO();
        urlShortenerResponseDTO.setLongLink(urlToRet.getLongLink());
        urlShortenerResponseDTO.setShortLink(shortLink);

        response.sendRedirect(urlToRet.getLongLink());
        return new ResponseEntity<>(urlShortenerResponseDTO, HttpStatus.OK);
    }

}