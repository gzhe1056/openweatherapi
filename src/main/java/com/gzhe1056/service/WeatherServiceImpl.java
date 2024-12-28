package com.gzhe1056.service;
import com.gzhe1056.modal.WeatherData;
import com.gzhe1056.repository.WeatherDataRepository;
import com.gzhe1056.response.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final WeatherDataRepository weatherDataRepository;

    public WeatherServiceImpl(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    @Override
    public WeatherData getWeather(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);

        WeatherData weatherData = new WeatherData(
                city,
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                response.getWeather()[0].getDescription());
        weatherDataRepository.save(weatherData);
        return weatherData;
    }
}
