package com.starwars.controller;

import com.starwars.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/java-performance/api/v1/starwars")
public class StarWarsController {

    @Autowired
    private StarWarsService starWarsService;
    private final String filePath = "files/The_Uncertain_Path.txt";


    @GetMapping("/vehicles")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getStarWarsVehicles() throws MalformedURLException {
        return starWarsService.getStarWarsVehicles();
    }


    @GetMapping("/slow-vehicles")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getStarWarsSlowVehicles() throws MalformedURLException {
        return starWarsService.getSlowVehicles();
    }

    @GetMapping(value = "/extract", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> getStract() {
        return starWarsService.readFileBytes(filePath)
                .map(bytes -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(bytes));
    }

    @GetMapping("/vehicle/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getVehicleByName(@PathVariable("name") String name) throws MalformedURLException {
        //return starWarsService.findVehicleByNameOptimized(name);
        return starWarsService.findVehicleByName(name);
    }


}
