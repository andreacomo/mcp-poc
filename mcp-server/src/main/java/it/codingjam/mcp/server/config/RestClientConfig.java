package it.codingjam.mcp.server.config;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient geoLocationRestClient(RestClient.Builder builder) {
        return builder.baseUrl("https://geocoding-api.open-meteo.com")
                .build();
    }

    @Bean
    RestClient weatherRestClient(RestClient.Builder builder) {
        return builder.baseUrl("https://api.open-meteo.com")
                .build();
    }
}
