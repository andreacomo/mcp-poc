package it.codingjam.mcp.server.config;

import it.codingjam.mcp.server.tools.GeoLocationTools;
import it.codingjam.mcp.server.tools.WeatherTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfig {


    @Bean
    public ToolCallbackProvider weatherToolsProvider(
            GeoLocationTools geoLocationTools,
            WeatherTools weatherTools) {
        return  MethodToolCallbackProvider.builder()
                .toolObjects(geoLocationTools, weatherTools)
                .build();
    }
}
