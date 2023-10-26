package com.urlshortenerh2.controller;

import com.urlshortenerh2.model.MostBrowserCreationsRequestResponseDTO;
import com.urlshortenerh2.model.MostPopularBrowserResponseDTO;
import com.urlshortenerh2.model.MostVisitedLinkResponseDTO;
import com.urlshortenerh2.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")

public class UrlStatisticsController {


    @Autowired
    private UrlStatisticsService statisticsService;

    @GetMapping("/most_visits")
    public MostVisitedLinkResponseDTO getMostVisitedSites() {
        return statisticsService.getMostVisitedLinks();
    }

    @GetMapping("/browser_most_created_links")
    public MostBrowserCreationsRequestResponseDTO getMostCreationResponse() {
        return statisticsService.getMostBrowserCreationRequest();

    }

    @GetMapping("/most_popular_browsers")
    public MostPopularBrowserResponseDTO getMostPopularBrowser() {
        return statisticsService.getMostPopularBrowsers();
    }

}

