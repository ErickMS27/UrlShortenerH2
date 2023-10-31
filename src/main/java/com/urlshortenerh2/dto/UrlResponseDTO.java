package com.urlshortenerh2.dto;

import java.time.LocalDateTime;

public class UrlResponseDTO {
    private String longLink;
    private String shortLink;
    private LocalDateTime estimatedTime;

    public UrlResponseDTO(String longLink, String shortLink, LocalDateTime expirationDate) {
        this.longLink = longLink;
        this.shortLink = shortLink;
        this.estimatedTime = estimatedTime;
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

    public LocalDateTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalDateTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "UrlResponseDto{" +
                "longLink='" + longLink + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}