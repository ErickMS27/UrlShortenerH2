package com.urlshortenerh2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "url_shortener", schema = "urlshortener")
public class UrlShortener {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Lob
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String originalUrl;

    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String shortLink;

    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime creationDate;

    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime expirationDate;

    public UrlShortener(long id, String originalUrl, String shortLink, LocalDateTime creationDate, LocalDateTime expirationDate) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public UrlShortener() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}