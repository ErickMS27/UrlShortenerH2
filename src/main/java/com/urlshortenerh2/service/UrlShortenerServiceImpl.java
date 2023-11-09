package com.urlshortenerh2.service;

import com.google.common.hash.Hashing;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.exception.UrlNotFoundException;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.repository.UrlShortenerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UrlShortenerServiceImpl implements UrlShortenerService {
    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);
    private UrlShortenerRequestDTO urlShortenerRequestDTO;

    @Override
    public @NotNull @NotEmpty String generateShortLink(UrlShortenerRequestDTO urlShortenerRequestDTO) {
        UrlShortener urlToRet = urlShortenerRepository.findByLongLink(urlShortenerRequestDTO.getLongLink());

        if (urlToRet != null) {
            return urlToRet.getShortLink();
        } else {
            String encodedUrl = encodeUrl(urlShortenerRequestDTO.getLongLink());
            UrlShortener urlToPersist = new UrlShortener();
            urlToPersist.setLongLink(urlShortenerRequestDTO.getLongLink());
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

    @Override
    public List<UrlShortener> getTop10VisitedLinks() {
        return urlShortenerRepository.findTop10ByOrderByVisitCountDesc();
    }

    @Override
    public Long countMostAccessedViews(UrlShortenerRequestDTO urlShortenerRequestDTO) {
        List<UrlShortener> urlList = urlShortenerRepository.findAll();
        Long maxViews = urlList.stream()
                .mapToLong(UrlShortener::getVisitCount)
                .max()
                .orElse(0);
        return maxViews;
    }

    @Override
    public Long increaseAccessedViews(UrlShortenerRequestDTO urlShortenerRequestDTO) {
        String longLink = urlShortenerRequestDTO.getLongLink();

        UrlShortener urlToRet = urlShortenerRepository.findByLongLink(longLink);

        if (urlToRet != null) {
            if (urlToRet.getVisitCount() == null) {
                urlToRet.setVisitCount(0L);
            }
            urlToRet.setVisitCount(urlToRet.getVisitCount() + 1);
            urlShortenerRepository.save(urlToRet);
            return urlToRet.getVisitCount();
        } else {
            throw new UrlNotFoundException("URL longo não encontrado: " + longLink);
        }
    }

}