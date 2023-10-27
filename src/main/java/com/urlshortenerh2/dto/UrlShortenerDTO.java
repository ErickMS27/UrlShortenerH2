package com.urlshortenerh2.dto;

public class UrlShortenerDTO {
    private String url;
    private String expirationDate;

    public UrlShortenerDTO(String url, String expirationDate) {
        this.url = url;
        this.expirationDate = expirationDate;
    }

    public UrlShortenerDTO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "url='" + url + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}