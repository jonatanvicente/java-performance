package com.starwars.controller;

import com.starwars.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/java-performance/api/v1/starwars")
public class StarWarsController {

    @Autowired
    private StarWarsService service;
    @GetMapping("/vehicles")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getStarWarsVehicles() throws MalformedURLException {
        return service.getStarWarsVehicles();
    }


    @GetMapping("/slow-vehicles")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getStarWarsSlowVehicles() throws MalformedURLException {
        return service.getSlowVehicles();
    }

}
