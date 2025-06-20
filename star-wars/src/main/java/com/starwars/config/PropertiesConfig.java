package com.starwars.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("url")
@Getter
@Setter
public class PropertiesConfig {

    //private Integer connection_timeout;//millis
    private String ds_test;
    //private Integer maxBytesInMemory;

    private String secret;
    private String headerString;
    private String authoritiesClaim;
    private String err;


}