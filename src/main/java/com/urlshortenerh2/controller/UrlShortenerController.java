package com.urlshortenerh2.controller;

import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.dto.UrlShortenerDTO;
import com.urlshortenerh2.dto.UrlErrorResponseDTO;
import com.urlshortenerh2.dto.UrlResponseDTO;
import com.urlshortenerh2.service.UrlShortenerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key")
public class UrlShortenerController
{
    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlShortenerDTO urlShortenerDTO)
    {
        String longLink = urlShortenerDTO.getUrl();

        String shortLink = urlShortenerService.generateShortLink(longLink, urlShortenerDTO);

        UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
        urlResponseDTO.setLongLink(longLink);
        urlResponseDTO.setShortLink(shortLink);
        return new ResponseEntity<UrlResponseDTO>(urlResponseDTO, HttpStatus.OK);
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

        UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
        urlResponseDTO.setLongLink(urlToRet.getLongLink());
        urlResponseDTO.setShortLink(urlToRet.getShortLink());
        urlResponseDTO.setEstimatedTime(urlToRet.getEstimatedTime());

        return new ResponseEntity<>(urlResponseDTO, HttpStatus.OK);
    }

//    @PostMapping
//    public void register(@RequestBody CalculateEstimatedTime calculateEstimatedTime){
//        System.out.println(calculateEstimatedTime);
//    }
}