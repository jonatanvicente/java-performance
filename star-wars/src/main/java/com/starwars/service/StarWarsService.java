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

@Slf4j
@Service
public class StarWarsService {

    @Autowired
    PropertiesConfig config;

    @Autowired
    HttpProxy httpProxy;
    public Mono<StarWarsVehiclesResultDto> getStarWarsVehicles() throws MalformedURLException{
           return httpProxy.getRequestData(new URL(config.getDs_test()), StarWarsVehiclesResultDto.class);
    }
    private Mono<StarWarsVehiclesResultDto> logInternalErrorReturnDefaultPage(Throwable exception) {
        log.error("Error: "+exception.getMessage());
        return Mono.just(new StarWarsVehiclesResultDto());
    }

}
