package com.weather.model;

import java.sql.Timestamp;

public class WeatherHistoryEntry {
    private final int id;
    private final String city;
    private final double temperature;
    private final double feelsLike;
    private final String condition;
    private final int humidity;
    private final double windSpeed;
    private final Timestamp searchedAt;

    public WeatherHistoryEntry(
            int id,
            String city,
            double temperature,
            double feelsLike,
            String condition,
            int humidity,
            double windSpeed,
            Timestamp searchedAt) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.condition = condition;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.searchedAt = searchedAt;
    }

    public Object[] toTableRow() {
        return new Object[]{
                id,
                city,
                temperature,
                feelsLike,
                condition,
                humidity,
                windSpeed,
                searchedAt
        };
    }
}