package com.weather;

import com.weather.gui.WeatherFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherFrame::new);
    }
}