package com.weather.gui;

import com.weather.model.Weather;
import com.weather.model.WeatherHistoryEntry;
import com.weather.repository.WeatherRepository;
import com.weather.service.WeatherService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class WeatherFrame extends JFrame {

    private final JTextField cityField;

    private final JLabel cityLabel;
    private final JLabel temperatureLabel;
    private final JLabel conditionLabel;
    private final JLabel feelsLikeLabel;
    private final JLabel humidityLabel;
    private final JLabel windLabel;

    private final JButton searchButton;
    private final JButton historyButton;

    private final WeatherService weatherService = new WeatherService();
    private final WeatherRepository weatherRepository = new WeatherRepository();

    private final Color backgroundColor = new Color(225, 240, 255);
    private final Color cardColor = Color.WHITE;
    private final Color primaryColor = new Color(35, 105, 190);
    private final Color textColor = new Color(35, 50, 70);

    public WeatherFrame() {
        setTitle("Weather Information App");
        setSize(750, 650);
        setMinimumSize(new Dimension(650, 580));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
        mainPanel.setBackground(backgroundColor);

        // Header
        JLabel titleLabel = new JLabel("Weather Dashboard");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(textColor);

        JLabel subtitleLabel = new JLabel("Discover the weather in your city");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitleLabel.setForeground(new Color(90, 110, 130));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitleLabel);

        // Search section
        cityField = new JTextField();
        cityField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        cityField.setPreferredSize(new Dimension(300, 45));
        cityField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 215, 230)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        searchButton = createButton("Search Weather", primaryColor);
        historyButton = createButton("View Search History", new Color(70, 90, 115));

        JPanel searchPanel = new JPanel(new BorderLayout(12, 0));
        searchPanel.setOpaque(false);
        searchPanel.add(cityField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Main weather card
        JPanel weatherCard = createCard();
        weatherCard.setLayout(new BorderLayout(10, 10));
        weatherCard.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        cityLabel = new JLabel("Enter a city to view its weather", SwingConstants.CENTER);
        cityLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        cityLabel.setForeground(textColor);

        temperatureLabel = new JLabel("-- °C", SwingConstants.CENTER);
        temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 52));
        temperatureLabel.setForeground(primaryColor);

        conditionLabel = new JLabel("Weather condition", SwingConstants.CENTER);
        conditionLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        conditionLabel.setForeground(new Color(90, 110, 130));

        JPanel mainWeatherInfo = new JPanel();
        mainWeatherInfo.setOpaque(false);
        mainWeatherInfo.setLayout(new BoxLayout(mainWeatherInfo, BoxLayout.Y_AXIS));

        cityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        conditionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainWeatherInfo.add(cityLabel);
        mainWeatherInfo.add(Box.createVerticalStrut(15));
        mainWeatherInfo.add(temperatureLabel);
        mainWeatherInfo.add(Box.createVerticalStrut(8));
        mainWeatherInfo.add(conditionLabel);

        weatherCard.add(mainWeatherInfo, BorderLayout.CENTER);

        // Weather detail cards
        feelsLikeLabel = new JLabel("Feels Like: -- °C", SwingConstants.CENTER);
        humidityLabel = new JLabel("Humidity: -- %", SwingConstants.CENTER);
        windLabel = new JLabel("Wind: -- m/s", SwingConstants.CENTER);

        JPanel detailsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        detailsPanel.setOpaque(false);
        detailsPanel.add(createDetailCard("Feels Like", feelsLikeLabel));
        detailsPanel.add(createDetailCard("Humidity", humidityLabel));
        detailsPanel.add(createDetailCard("Wind Speed", windLabel));

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.add(historyButton);

        // Center content
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        weatherCard.setPreferredSize(new Dimension(600, 250));
        weatherCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        detailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        contentPanel.add(searchPanel);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(weatherCard);
        contentPanel.add(Box.createVerticalStrut(18));
        contentPanel.add(detailsPanel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(bottomPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        searchButton.addActionListener(e -> searchWeather());
        cityField.addActionListener(e -> searchWeather());
        historyButton.addActionListener(e -> showSearchHistory());

        setVisible(true);
    }

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createLineBorder(new Color(215, 225, 235)));
        return card;
    }

    private JPanel createDetailCard(String title, JLabel valueLabel) {
        JPanel card = createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(100, 115, 130));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        valueLabel.setForeground(textColor);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);

        return card;
    }

    private JButton createButton(String text, Color color) {
    JButton button = new JButton(text);

    button.setFont(new Font("SansSerif", Font.BOLD, 14));
    button.setForeground(Color.WHITE);
    button.setBackground(color);

    // Force Swing to use our custom button colors
    button.setOpaque(true);
    button.setContentAreaFilled(true);
    button.setBorderPainted(false);
    button.setFocusPainted(false);

    button.setBorder(
            BorderFactory.createEmptyBorder(12, 18, 12, 18)
    );

    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    return button;
}

    private void searchWeather() {
        String city = cityField.getText().trim();

        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a city name.",
                    "Missing City",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        searchButton.setEnabled(false);
        searchButton.setText("Searching...");

        SwingWorker<Weather, Void> worker = new SwingWorker<>() {
            @Override
            protected Weather doInBackground() throws Exception {
                return weatherService.getWeather(city);
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                searchButton.setText("Search Weather");

                try {
                    Weather weather = get();

                    weatherRepository.saveWeather(weather);

                    cityLabel.setText(weather.getCity());
                    temperatureLabel.setText(
                            String.format("%.1f °C", weather.getTemperature())
                    );
                    conditionLabel.setText(weather.getCondition());

                    feelsLikeLabel.setText(
                            String.format("%.1f °C", weather.getFeelsLike())
                    );
                    humidityLabel.setText(weather.getHumidity() + " %");
                    windLabel.setText(
                            String.format("%.1f m/s", weather.getWindSpeed())
                    );

                } catch (Exception ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            WeatherFrame.this,
                            "Could not fetch weather or save it to the database.\n"
                                    + ex.getMessage(),
                            "Weather Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };

        worker.execute();
    }

    private void showSearchHistory() {
        try {
            List<WeatherHistoryEntry> history =
                    weatherRepository.getHistory();

            if (history.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No search history found.",
                        "Search History",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            String[] columns = {
                    "ID", "City", "Temperature (°C)", "Feels Like (°C)",
                    "Condition", "Humidity (%)", "Wind (m/s)", "Searched At"
            };

            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (WeatherHistoryEntry entry : history) {
                model.addRow(entry.toTableRow());
            }

            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            TableRowSorter<DefaultTableModel> sorter =
                    new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            JTextField filterField = new JTextField();

            filterField.getDocument().addDocumentListener(
                    new DocumentListener() {
                        private void filterTable() {
                            String text = filterField.getText().trim();

                            if (text.isEmpty()) {
                                sorter.setRowFilter(null);
                            } else {
                                sorter.setRowFilter(
                                        RowFilter.regexFilter(
                                                "(?i)" + Pattern.quote(text),
                                                1
                                        )
                                );
                            }
                        }

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            filterTable();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            filterTable();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            filterTable();
                        }
                    }
            );

            JPanel searchPanel = new JPanel(new BorderLayout(8, 8));
            searchPanel.setBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            );
            searchPanel.add(new JLabel("Filter by city:"), BorderLayout.WEST);
            searchPanel.add(filterField, BorderLayout.CENTER);

            JDialog dialog = new JDialog(this, "Weather Search History", true);
            dialog.setLayout(new BorderLayout());
            dialog.add(searchPanel, BorderLayout.NORTH);
            dialog.add(new JScrollPane(table), BorderLayout.CENTER);
            dialog.setSize(1000, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load search history.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}