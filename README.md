# Model Context Protocol POC

This project aims to play with MCP and Spring AI

## MCP Server

STDIO based communication implementation. 
Based on [Official walkthrough](https://modelcontextprotocol.io/quickstart/server#java)

The server exposes two tools:

* `get_city_geolocation`: get latitude and longitude from a city name
* `get_weather_forecast`: retrieve weather forecast for the given latitude and longitude. Default is 7 days

### How to inspect the server

* build the project:
  ```shell
  mvn clean package
  ```
* Run the inspector (requires Node.js)
  ```shell
  npx @modelcontextprotocol/inspector
  ```
* Go to `http://localhost:6274` and set:
  * Transport Type: `STDIO`
  * Command: `java`
  * Arguments: `-jar /absolute/path/to/mcp-server-1.0.0-SNAPSHOT.jar`
  * Connect

Now you can explore the MPC server

### How to connect the MCP Server to Claude Desktop

* Locate the file `claude_desktop_config.json`:
  * macOS: `~/Library/Application Support/Claude/claude_desktop_config.json`
  * Windows: `%APPDATA%\Claude\claude_desktop_config.json`
  * *Linux not yet supported by Claude Desktop*
* Add a new entry in the `mcpServers` object:
  ```json
    {
      "mcpServers": {
        "spring-ai-mcp-weather": {
          "command": "java",
          "args": [
            "-jar",
            "/absolute/path/to/mcp-server-1.0.0-SNAPSHOT.jar"
          ]
        }
      }
   }
  ```
* Try to ask Claude "what's the forecast for tomorrow in <your city>?". Claude will be capable of understand that need to retrieve geo info to give you the answer (using both tools)