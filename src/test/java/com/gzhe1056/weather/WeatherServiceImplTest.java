package com.gzhe1056.weather;

import com.gzhe1056.modal.WeatherData;
import com.gzhe1056.repository.WeatherDataRepository;
import com.gzhe1056.response.OpenWeatherResponse;
import com.gzhe1056.service.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceImplTest {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    public WeatherServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Test
    void testGetWeather_Success() {
        String city = "London";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=" + apiKey + "&units=metric";

        OpenWeatherResponse.Main main = new OpenWeatherResponse.Main();
        main.setTemp(15.0);
        main.setHumidity(80);

        OpenWeatherResponse.Weather weather = new OpenWeatherResponse.Weather();
        weather.setDescription("Clear sky");
        OpenWeatherResponse response = new OpenWeatherResponse();
        response.setMain(main);
        response.setWeather(new OpenWeatherResponse.Weather[]{weather});

        when(restTemplate.getForObject(url, OpenWeatherResponse.class)).thenReturn(response);

        WeatherData mockWeatherData = new WeatherData(city, 15.0, 80, "Clear sky");
        when(weatherDataRepository.save(any(WeatherData.class))).thenReturn(mockWeatherData);

        WeatherData result = weatherService.getWeather(city);

        assertNotNull(result);
        assertEquals("London", result.getCity());
        assertEquals(15.0, result.getTemperature());
        assertEquals(80, result.getHumidity());
        assertEquals("Clear sky", result.getDescription());

        verify(weatherDataRepository, times(1)).save(any(WeatherData.class));
    }
}
