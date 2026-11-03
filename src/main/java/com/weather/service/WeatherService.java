package com.weather.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weather.model.Weather;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherService {

    private static final String API_KEY = "bfcdd2c019d574a9f48deb00456258f6";
    private static final String BASE_URL =
            "https://api.openweathermap.org/data/2.5/weather";

    private final HttpClient client = HttpClient.newHttpClient();

    public Weather getWeather(String city) throws Exception {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        String url = BASE_URL + "?q=" + encodedCity
                + "&appid=" + API_KEY
                + "&units=metric";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException("Weather API error: " + response.body());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        String cityName = json.get("name").getAsString();

        JsonObject main = json.getAsJsonObject("main");
        double temperature = main.get("temp").getAsDouble();
        double feelsLike = main.get("feels_like").getAsDouble();
        int humidity = main.get("humidity").getAsInt();

        String condition = json.getAsJsonArray("weather")
                .get(0).getAsJsonObject()
                .get("description").getAsString();

        double windSpeed = json.getAsJsonObject("wind")
                .get("speed").getAsDouble();

        return new Weather(cityName, temperature, feelsLike,
                condition, humidity, windSpeed);
    }
}