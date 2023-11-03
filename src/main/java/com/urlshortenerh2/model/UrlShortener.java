package com.urlshortenerh2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity (name = "links")
@Getter
@Setter
@Table(name = "links")
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
    private LocalDateTime estimatedTime;

    @Column(name = "createdTime")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private LocalDateTime createdTime;

}