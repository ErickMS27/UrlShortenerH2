package com.urlshortenerh2.service;

import com.google.common.hash.Hashing;
import com.urlshortenerh2.dto.UrlDetailDTO;
import com.urlshortenerh2.dto.UrlShortenerRequestDTO;
import com.urlshortenerh2.exception.UrlNotFoundException;
import com.urlshortenerh2.model.UrlShortener;
import com.urlshortenerh2.repository.UrlShortenerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Random;

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

    public String generateShortLinkUrl(String longUrl) {
        Random random = new Random();
        byte[] randomBytes = new byte[6];
        random.nextBytes(randomBytes);
        String shortUrl = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        String websiteName = getWebsiteName(longUrl);
        String combinedShortUrl = websiteName + "/" + shortUrl;

        return combinedShortUrl;
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

    public UrlShortener updateLongLink(Long id, String longLink){
        UrlShortener settedShortedLink = urlShortenerRepository.findById(id).orElse(null);
        if (settedShortedLink != null) {
            settedShortedLink.setLongLink(longLink);
            String newShortedLink = generateShortLinkUrl(longLink);
            settedShortedLink.setShortLink(newShortedLink);
            return urlShortenerRepository.save(settedShortedLink);
        }else{
            return null;
        }
    }

    public UrlDetailDTO detailUrlForId(Long id) {
        UrlShortener urlShortener = urlShortenerRepository.findById(id).orElse(null);
        if (urlShortener != null){
            return new UrlDetailDTO(urlShortener);
        } else {
        return null;
        }
    }

    private static String getWebsiteName(String websiteName) {
        websiteName = websiteName.toLowerCase();
        if (websiteName.contains("http") || websiteName.contains("www")) {
            websiteName = websiteName.substring(websiteName.indexOf(".") + 1);
        }
        return websiteName;
    }

    public void deleteUrlForId(Long id){
        urlShortenerRepository.deleteById(id);
    }

public Page<UrlDetailDTO> listPage(Pageable pages){
    return urlShortenerRepository.findAllProjectBy(pages);
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