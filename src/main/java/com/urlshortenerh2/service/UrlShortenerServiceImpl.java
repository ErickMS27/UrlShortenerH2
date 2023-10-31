package com.urlshortenerh2.service;

import com.google.common.hash.Hashing;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.dto.UrlShortenerDTO;
import com.urlshortenerh2.repository.UrlShortenerRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);
    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    @Override
    public @NotNull @NotEmpty String generateShortLink(String longLink, UrlShortenerDTO urlShortenerDTO) {
        UrlShortener urlToRet = urlShortenerRepository.findByLongLink(longLink);

        if (urlToRet != null) {
            return urlToRet.getShortLink();
        } else {
            String encodedUrl = encodeUrl(urlShortenerDTO.getUrl());
            UrlShortener urlToPersist = new UrlShortener();
            urlToPersist.setCreatedTime(LocalDateTime.now());
            urlToPersist.setLongLink(urlShortenerDTO.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            urlShortenerRepository.save(urlToPersist);
            return encodedUrl;
        }
    }

    public String encodeUrl(String url) {
        if (url != null) {
            String encodedUrl = "";
            LocalDateTime time = LocalDateTime.now();
            encodedUrl = Hashing.murmur3_32()
                    .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                    .toString();
            return encodedUrl;
        } else {
            return null;
        }
    }

    @Override
    public UrlShortener persistShortLink(UrlShortener urlShortener) {
        UrlShortener urlToRet = urlShortenerRepository.save(urlShortener);
        return urlToRet;
    }

    @Override
    public UrlShortener getEncodedUrl(String urlShortener) {
        UrlShortener urlToRet = urlShortenerRepository.findByShortLink(urlShortener);
        return urlToRet;
    }

    @Override
    public void deleteShortLink(UrlShortener urlShortener) {

        urlShortenerRepository.delete(urlShortener);
    }
}