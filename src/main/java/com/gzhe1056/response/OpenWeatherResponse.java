package com.gzhe1056.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse {
    private Main main;
    private Weather[] weather;

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }
    }
}

