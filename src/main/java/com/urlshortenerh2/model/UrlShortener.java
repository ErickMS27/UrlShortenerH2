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
    @Column(name = "originalUrl")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String originalUrl;

    @Column(name = "shortLink")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String shortLink;

    @Column(name = "estimatedTime")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime estimatedTime;

    @Column(name = "createdTime")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime createdTime;

//    public UrlShortener(long id, String originalUrl, String shortLink, LocalDateTime estimatedTime, LocalDateTime createdTime) {
//        this.id = id;
//        this.originalUrl = originalUrl;
//        this.shortLink = shortLink;
//        this.estimatedTime = estimatedTime;
//        this.createdTime = createdTime;
//    }

    public UrlShortener() {
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", createdTime=" + createdTime +
                '}';
    }

}