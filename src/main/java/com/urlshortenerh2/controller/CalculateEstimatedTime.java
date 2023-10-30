package com.urlshortenerh2.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CalculateEstimatedTime {

    LocalDateTime estimatedTime;
    LocalDateTime createdTime;

    public CalculateEstimatedTime (LocalDateTime estimatedTime, LocalDateTime createdTime) {

        this.estimatedTime = estimatedTime;
        this.createdTime = createdTime;
    }

    public void estimateTime (int numOfTasks){
        int totalEstimatedTime = numOfTasks * 5;
        LocalDateTime updatedEstimatedTime = estimatedTime.plusSeconds(totalEstimatedTime);
        this.estimatedTime = updatedEstimatedTime;

    }

}
