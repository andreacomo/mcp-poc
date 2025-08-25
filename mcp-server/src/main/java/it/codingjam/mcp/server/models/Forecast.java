package it.codingjam.mcp.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Forecast(
        double latitude,
        double longitude,
        String timezone,
        float elevation,
        @JsonProperty("hourly_units")
        HourlyUnits hourlyUnits,
        HourlyData hourly
) {

    record HourlyUnits(
            String time,
            @JsonProperty("temperature_2m")
            String temperature2m,
            @JsonProperty("precipitation_probability")
            String precipitationProbability
    ) {}

    record HourlyData(
            List<String> time,
            @JsonProperty("temperature_2m")
            List<Float> temperature2m,
            @JsonProperty("precipitation_probability")
            List<Integer> precipitationProbability
    ) {}
}
