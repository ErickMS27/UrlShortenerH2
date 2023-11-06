package com.urlshortenerh2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class LinkCounts {

    @Column(name = "longLink")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String longLink;

    @Column(name = "shortLink")
    @NotNull(message = "{field.notnull}")
    @NotEmpty(message = "{field.notempty}")
    private String shortLink;

      @Column(name = "visitCount")
      @NotNull(message = "{field.notnull}")
      @NotEmpty(message = "{field.notempty}")
      public int visitCount;

    public LinkCounts(String longLink, String shortLink) {
        this.longLink = longLink;
        this.shortLink = shortLink;
        this.visitCount = 0;
    }

    public void increaseVisitCount(){
        visitCount++;
    }
}
