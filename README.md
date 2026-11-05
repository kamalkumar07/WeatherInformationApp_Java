🌦️ Weather Information App

A desktop weather application built with Java Swing, OpenWeather API, and MySQL. It allows users to search for real-time weather conditions by city, view weather details in a dashboard, and store and browse previous searches.

✨ Features

* 🌍 Search live weather by city name
* 🌡️ Display temperature and feels-like temperature
* ☁️ Show weather conditions
* 💧 Display humidity
* 💨 Display wind speed
* 💾 Automatically save successful searches to MySQL
* 📜 View weather search history
* 🔎 Filter search history by city
* 🖥️ User-friendly Java Swing dashboard

🛠️ Technologies Used

Technology	Purpose
Java 21	Application development
Java Swing	Desktop GUI
OpenWeather API	Live weather data
MySQL	Store weather search history
JDBC	Java-MySQL connectivity
Maven	Dependency management
Gson	Parse JSON API responses
VS Code	Development environment

📸 Application Preview

The application provides a dashboard with a city search field, current weather information, weather detail cards, and access to search history.

📁 Project Structure

WeatherInformationApp/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── weather/
│                   ├── Main.java
│                   ├── WeatherApiTest.java
│                   ├── DatabaseTest.java
│                   ├── DatabaseSaveTest.java
│                   ├── database/
│                   │   └── DatabaseConnection.java
│                   ├── gui/
│                   │   └── WeatherFrame.java
│                   ├── model/
│                   │   ├── Weather.java
│                   │   └── WeatherHistoryEntry.java
│                   ├── repository/
│                   │   └── WeatherRepository.java
│                   └── service/
│                       └── WeatherService.java
├── pom.xml
└── README.md

⚙️ Prerequisites

Make sure you have the following installed:

* Java JDK 21 or later
* Apache Maven
* MySQL Server
* OpenWeather API key

🚀 Setup and Installation

1. Clone the repository

git clone https://github.com/YOUR_USERNAME/WeatherInformationApp.git
cd WeatherInformationApp

2. Configure the OpenWeather API

Create an API key through OpenWeather.

Configure the API key in WeatherService.java or, preferably, use an environment variable so that the key is not committed to GitHub.

For example, read it in Java using:

String apiKey = System.getenv("OPENWEATHER_API_KEY");

Set the environment variable in your terminal:

export OPENWEATHER_API_KEY="your_api_key_here"

Restart VS Code or launch the application from the same terminal so it can access the variable.

3. Configure MySQL

Start your MySQL server and create the database:

CREATE DATABASE weather_app;
USE weather_app;
CREATE TABLE weather_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    temperature DOUBLE,
    feels_like DOUBLE,
    condition_text VARCHAR(100),
    humidity INT,
    wind_speed DOUBLE,
    searched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

Update the database connection settings in:

src/main/java/com/weather/database/DatabaseConnection.java

Configure the correct MySQL username and password for your local environment.

4. Build the project

mvn clean package

5. Run the application

Run Main.java from VS Code, or use Maven to execute it if your project is configured with an appropriate Java execution plugin.

🧭 How to Use

1. Launch the application.
2. Enter a city name in the search field.
3. Click Search Weather.
4. View the current weather information.
5. Successful searches are automatically saved to MySQL.
6. Click View Search History to browse previous searches.
7. Use the city filter to find specific records.

🗄️ Database

The application stores the following information for each successful search:

* City
* Temperature
* Feels-like temperature
* Weather condition
* Humidity
* Wind speed
* Search timestamp

🔐 Security Note

Do not commit API keys, database passwords, or other credentials to GitHub. Use environment variables or local configuration files excluded through .gitignore.

🔮 Future Enhancements

* Weather forecasts
* Favorite cities
* Weather condition icons
* Additional weather metrics
* Export search history to CSV
* Improved error handling and loading indicators

👨‍💻 Author

Mohan Kamal Kumar
Siddharth Goyal
Tanushvi

Computer Science Engineering Student

📄 License

This project is developed for educational and learning purposes.
