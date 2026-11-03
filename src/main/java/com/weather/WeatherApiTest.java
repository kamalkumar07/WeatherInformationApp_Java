package com.weather;

import com.weather.model.Weather;
import com.weather.service.WeatherService;

public class WeatherApiTest {
    public static void main(String[] args) {
        try {
            WeatherService service = new WeatherService();

            Weather weather = service.getWeather("Visakhapatnam");

            System.out.println("City: " + weather.getCity());
            System.out.println("Temperature: " + weather.getTemperature() + " °C");
            System.out.println("Feels Like: " + weather.getFeelsLike() + " °C");
            System.out.println("Condition: " + weather.getCondition());
            System.out.println("Humidity: " + weather.getHumidity() + "%");
            System.out.println("Wind Speed: " + weather.getWindSpeed() + " m/s");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}