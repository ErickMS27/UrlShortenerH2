package com.urlshortenerh2.dto;

import com.urlshortenerh2.model.UrlShortener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlDetailDTO {

    Long id;
    String shortLink;
    String longLink;
    Long visitCounts;



    public UrlDetailDTO(UrlShortener urlShortener){
         this(urlShortener.getId(), urlShortener.getShortLink(), urlShortener.getShortLink(), urlShortener.getLongLink(),
                urlShortener.getVisitCount());
    }

    public UrlDetailDTO(Long id, String shortLink, String shortLink1, String longLink, Long visitCount) {
        this.id = id;
        this.shortLink = shortLink;
        this.longLink = longLink;
        this.visitCounts = visitCount;
    }
}
