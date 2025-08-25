package it.codingjam.mcp.server.tools;

import it.codingjam.mcp.server.models.GeoLocation;
import it.codingjam.mcp.server.models.ListResult;
import it.codingjam.mcp.server.repositories.GeoLocationRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeoLocationTools {

    private final GeoLocationRepository geoLocationRepository;

    public GeoLocationTools(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }

    @Tool(
            name = "get_city_geolocation",
            description = """
                Retrieve the geo location (latitude and longitude) for the given city.
                The method return an array of objects, where the attribute names are meaningful.
                The fields "admin1", "admin2", "admin3" and "admin4" refers to the administrative level of the city
            """)
    public List<GeoLocation> getGeoLocationOfCity(String city) {
        ListResult<GeoLocation> response = geoLocationRepository.getGeoLocation(city, "IT");
        if (response.results() != null) {
            return response.results();
        } else {
            return List.of();
        }
    }
}
