package com.gzhe1056.service;

import com.gzhe1056.modal.WeatherData;

public interface WeatherService {
    WeatherData getWeather(String city);
}
