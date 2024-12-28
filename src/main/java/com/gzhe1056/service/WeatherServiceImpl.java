package com.gzhe1056.service;
import com.gzhe1056.modal.WeatherData;
import com.gzhe1056.repository.WeatherDataRepository;
import com.gzhe1056.response.OpenWeatherResponse;
import com.gzhe1056.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final WeatherDataRepository weatherDataRepository;

    public WeatherServiceImpl(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    @Override
    public WeatherData getWeather(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        logger.info("Calling OpenWeather API with URL: {}", url);

        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);

        if (response != null) {
            WeatherData weatherData = new WeatherData(
                    city,
                    response.getMain().getTemp(),
                    response.getMain().getHumidity(),
                    response.getWeather()[0].getDescription()
            );
            weatherDataRepository.save(weatherData);
            return weatherData;
        }

        return null;
    }

    private static class OpenWeatherResponse {
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
}
