package com.urlshortenerh2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity (name = "links")
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UrlShortener {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "longLink")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String longLink;

    @Column(name = "shortLink")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String shortLink;

    @Column(name = "estimatedTime")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime estimatedTime = LocalDateTime.now();

    @Column(name = "visitCount")
    @NotNull(message = "{field.notnull}")
    public Long visitCount;

    public UrlShortener (String short1, String long1){
        this.shortLink = short1;
        this.longLink = long1;
    }

    public UrlShortener (String short2, String long2, Long count){
        this.shortLink = short2;
        this.longLink = long2;
        this.visitCount = count;

    }

}