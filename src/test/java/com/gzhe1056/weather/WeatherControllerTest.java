package com.gzhe1056.weather;

import com.gzhe1056.controller.WeatherController;
import com.gzhe1056.modal.WeatherData;
import com.gzhe1056.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void testGetWeather_Success() throws Exception {
        String city = "London";
        WeatherData mockWeatherData = new WeatherData(city, 15.0, 80, "Clear sky");

        when(weatherService.getWeather(city)).thenReturn(mockWeatherData);

        mockMvc.perform(get("/weather/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("London"))
                .andExpect(jsonPath("$.temperature").value(15.0))
                .andExpect(jsonPath("$.humidity").value(80))
                .andExpect(jsonPath("$.description").value("Clear sky"));

        verify(weatherService, times(1)).getWeather(city);
    }

    @Test
    void testGetWeather_NotFound() throws Exception {
        String city = "UnknownCity";

        when(weatherService.getWeather(city)).thenReturn(null);

        mockMvc.perform(get("/weather/{city}", city))
                .andExpect(status().isNotFound());

        verify(weatherService, times(1)).getWeather(city);
    }
}

