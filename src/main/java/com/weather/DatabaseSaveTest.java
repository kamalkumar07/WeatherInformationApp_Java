package com.weather;

import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;
import com.weather.service.WeatherService;

public class DatabaseSaveTest {

    public static void main(String[] args) {
        try {
            WeatherService weatherService = new WeatherService();
            WeatherRepository repository = new WeatherRepository();

            Weather weather = weatherService.getWeather("Visakhapatnam");
            repository.saveWeather(weather);

            System.out.println("Weather search saved to MySQL!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}