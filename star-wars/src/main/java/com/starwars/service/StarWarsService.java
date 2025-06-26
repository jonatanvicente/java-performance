package com.starwars.service;

import com.starwars.config.PropertiesConfig;
import com.starwars.dto.StarWarsVehicleDto;
import com.starwars.dto.StarWarsVehiclesResultDto;
import com.starwars.proxy.HttpProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.io.InputStream;
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

    public Mono<List<Object>> getSlowVehicles(){
        while (true) {
            slowVehicles.add(new byte[100* 1024 * 1024]); // Add 1MB objects
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Mono<byte[]> readFileBytes(String filePath) {

        provokeFileLeak(filePath);

        try {
            Resource resource = new ClassPathResource(filePath);
            byte[] data = resource.getInputStream().readAllBytes();
            return Mono.just(data);
        } catch (IOException e) {
            return Mono.error(e);
        }
    }

    public void provokeFileLeak(String filePath) {
        List<InputStream> streams = new ArrayList<>();
        try {
            for (int i = 0; i < 1000000; i++) {
                Resource resource = new ClassPathResource(filePath);
                InputStream inputStream = resource.getInputStream(); // It doesn't close!!!
                streams.add(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Mono<StarWarsVehicleDto> findVehicleByNameOptimized(String name) throws MalformedURLException {
        return httpProxy.getRequestData(new URL(config.getDs_test()), StarWarsVehiclesResultDto.class)
                .flatMapMany(result -> Flux.fromArray(result.getResults()))
                .filter(vehicle -> name.equals(vehicle.getName()))
                .next(); // returns Mono<VehicleDto> with the first match
    }

    public Mono<StarWarsVehicleDto> findVehicleByName(String name) {
        return Mono.fromCallable(() -> {
            List<Integer> slowList = new ArrayList<>();
            for (int i = 0; i < 100_000; i++) {
                slowList.add(0, i);
            }

            StarWarsVehiclesResultDto result = httpProxy
                    .getRequestData(new URL(config.getDs_test()), StarWarsVehiclesResultDto.class)
                    .block();

            if (result != null && result.getResults() != null) {
                for (StarWarsVehicleDto vehicle : result.getResults()) {
                    if (name.equals(vehicle.getName())) {
                        return vehicle;
                    }
                }
            }
            return null;
        }).flatMap(vehicle -> vehicle != null ? Mono.just(vehicle) : Mono.empty());
    }
}
