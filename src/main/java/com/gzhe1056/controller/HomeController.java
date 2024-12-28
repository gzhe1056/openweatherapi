package com.gzhe1056.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> homePageHandler() {
        return new ResponseEntity<>("Backend for Weather API", HttpStatus.OK);
    }
}
