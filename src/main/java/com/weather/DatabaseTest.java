package com.weather;

import com.weather.database.DatabaseConnection;
import java.sql.Connection;

public class DatabaseTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("MySQL connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}