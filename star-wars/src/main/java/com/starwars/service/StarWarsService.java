package com.starwars.service;

import com.starwars.config.PropertiesConfig;
import com.starwars.dto.StarWarsVehiclesResultDto;
import com.starwars.proxy.HttpProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StarWarsService {

    @Autowired
    PropertiesConfig config;
    @Autowired
    HttpProxy httpProxy;

    List<Object> slowVehicles = new ArrayList<>();

    public Mono<StarWarsVehiclesResultDto> getStarWarsVehicles() throws MalformedURLException{
        return httpProxy.getRequestData(new URL(config.getDs_test()), StarWarsVehiclesResultDto.class);
    }

    public Mono<List<Object>> getSlowVehicles() throws MalformedURLException{
        while (true) {
            slowVehicles.add(new byte[100* 1024 * 1024]); // Add 1MB objects
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
