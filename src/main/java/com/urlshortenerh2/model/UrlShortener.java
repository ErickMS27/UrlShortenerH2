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
@Table(name = "links", schema = "urlshortener")
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
    private LocalDateTime estimatedTime;

    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime createdTime;

    public UrlShortener(long id, String originalUrl, String shortLink, LocalDateTime estimatedTime) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.estimatedTime = estimatedTime;
    }

    public UrlShortener() {
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", creationTime=" + estimatedTime +
                '}';
    }
}