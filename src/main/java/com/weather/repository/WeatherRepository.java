package com.weather.repository;

import com.weather.database.DatabaseConnection;
import com.weather.model.Weather;
import com.weather.model.WeatherHistoryEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {

    public void saveWeather(Weather weather) throws SQLException {
        String sql = """
                INSERT INTO weather_history
                (city, temperature, feels_like, condition_text, humidity, wind_speed)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, weather.getCity());
            statement.setDouble(2, weather.getTemperature());
            statement.setDouble(3, weather.getFeelsLike());
            statement.setString(4, weather.getCondition());
            statement.setInt(5, weather.getHumidity());
            statement.setDouble(6, weather.getWindSpeed());

            statement.executeUpdate();
        }
    }

    public List<WeatherHistoryEntry> getHistory() throws SQLException {
        List<WeatherHistoryEntry> history = new ArrayList<>();

        String sql = """
                SELECT id, city, temperature, feels_like,
                       condition_text, humidity, wind_speed, searched_at
                FROM weather_history
                ORDER BY searched_at DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                WeatherHistoryEntry entry = new WeatherHistoryEntry(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getDouble("temperature"),
                        resultSet.getDouble("feels_like"),
                        resultSet.getString("condition_text"),
                        resultSet.getInt("humidity"),
                        resultSet.getDouble("wind_speed"),
                        resultSet.getTimestamp("searched_at")
                );

                history.add(entry);
            }
        }

        return history;
    }
}