package com.jakil.graphql_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PlayerConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
