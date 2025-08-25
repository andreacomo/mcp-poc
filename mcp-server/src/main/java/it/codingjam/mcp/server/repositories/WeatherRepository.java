package it.codingjam.mcp.server.repositories;

import it.codingjam.mcp.server.models.Forecast;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class WeatherRepository {

    private final RestClient restClient;

    public WeatherRepository(RestClient weatherRestClient) {
        this.restClient = weatherRestClient;
    }

    public Forecast getForecastOf(int days, double latitude, double longitude) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("forecast_days", days)
                        .queryParam("hourly", "temperature_2m", "precipitation_probability")
                        .build())
                .retrieve()
                .body(Forecast.class);
    }
}
