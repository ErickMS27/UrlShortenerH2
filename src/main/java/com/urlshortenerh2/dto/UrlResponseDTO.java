package com.urlshortenerh2.dto;

import java.time.LocalDateTime;

public class UrlResponseDTO {
    private String longLink;
    private String shortLink;
    private LocalDateTime expirationDate;

    public UrlResponseDTO(String longLink, String shortLink, LocalDateTime expirationDate) {
        this.longLink = longLink;
        this.shortLink = shortLink;
        this.expirationDate = expirationDate;
    }

    public UrlResponseDTO() {
    }

    public String getLongLink() {
        return longLink;
    }

    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlResponseDto{" +
                "longLink='" + longLink + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}