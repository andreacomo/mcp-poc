package it.codingjam.mcp.server.models;

import java.util.List;

public record GeoLocation(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Float elevation,
        String timezone,
        Long population,
        List<String> postcodes,
        String country,
        String admin1,
        String admin2,
        String admin3,
        String admin4
) {
}
