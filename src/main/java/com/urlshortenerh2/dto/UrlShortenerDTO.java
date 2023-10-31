package com.urlshortenerh2.dto;

public class UrlShortenerDTO {
    private String url;

    public UrlShortenerDTO(String url) {
        this.url = url;

    }

    public UrlShortenerDTO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "url='" + url + '\'' +
                '}';
    }
}