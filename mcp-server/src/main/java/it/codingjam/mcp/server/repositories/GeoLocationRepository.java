package it.codingjam.mcp.server.repositories;

import it.codingjam.mcp.server.models.GeoLocation;
import it.codingjam.mcp.server.models.ListResult;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class GeoLocationRepository {

    private final RestClient restClient;

    public GeoLocationRepository(RestClient geoLocationRestClient) {
        this.restClient = geoLocationRestClient;
    }

    public ListResult<GeoLocation> getGeoLocation(String locationName, String countryCode) {
        return restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search")
                        .queryParam("name", locationName)
                        .queryParam("language", "it")
                        .queryParam("count", 10)
                        .queryParam("countryCode", countryCode)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
