package it.codingjam.mcp.server.tools;

import it.codingjam.mcp.server.models.Forecast;
import it.codingjam.mcp.server.repositories.WeatherRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTools {

    private final WeatherRepository weatherRepository;

    public WeatherTools(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Tool(
        name = "get_weather_forecast",
        description = """
            This method retrieve the next n days forecast, where n is the number of days. As default should be passed 7 days. Maximum is 16.
            The method return a json containing some general information and then:
            * the attribute 'hourly' has 3 arrays:
              * time is the most important. It defines the time scale of the data (hours granularity).
              * temperature_2m represents the values of the temperature for each hour
              * precipitation_probability represents the possible precipitation probability (from 0 to 100) per hour
            """
    )
    public Forecast getForecastForGetLocation(int days, double latitude, double longitude) {
        return weatherRepository.getForecastOf(days, latitude, longitude);
    }
}
