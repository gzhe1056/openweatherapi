package com.gzhe1056.controller;

import com.gzhe1056.modal.WeatherData;
import com.gzhe1056.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

//    public WeatherController(WeatherService weatherService) {
//        this.weatherService = weatherService;
//    }

//    @GetMapping("/{city}")
//    public WeatherData getWeather(@PathVariable String city) {
//        return weatherService.getWeather(city);
//    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherData> getWeatherHandler(@PathVariable String city) {
        WeatherData getWeatherData = weatherService.getWeather(city);
        return new ResponseEntity<>(getWeatherData, HttpStatus.OK) ;
    }

}