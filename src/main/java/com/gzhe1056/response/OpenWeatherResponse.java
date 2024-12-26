package com.gzhe1056.response;

public class OpenWeatherResponse {
    private Main main;
    private Weather[] weather;

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public static class Main {
        private double temp;
        private int humidity;

        public double getTemp() {
            return temp;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    public static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }
    }
}

