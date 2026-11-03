package com.weather.model;

public class Weather {
    private String city;
    private double temperature;
    private double feelsLike;
    private String condition;
    private int humidity;
    private double windSpeed;

    public Weather(String city, double temperature, double feelsLike,
                   String condition, int humidity, double windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.condition = condition;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public String getCondition() {
        return condition;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}