# Model Context Protocol POC

This project aims to play with [MCP](https://modelcontextprotocol.io/) and [Spring AI](https://docs.spring.io/spring-ai/reference/index.html).

This is a Maven Multi Module project

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

# MCP Client

The client is basically a **command line _chat_** based on OpenAI: thanks to Spring AI and MCP integration, the client connects to the MCP servers defined in [`claude_desktop_config.json`](mcp-client/src/main/resources/claude_desktop_config.json)
and create the **clients automatically** for each configured server.

> Please note that the `claude_desktop_config.json` contains **absolute paths** because is using STDIO communication:
> this type of communication requires the client to start the server, so the project **must me built first**!
> ```shell
> mvn package
> ```

> The client requires the OpenAI key, provided as env variable `OPENAI_API_KEY`.

**The client starts and list the available tools**, then is ready to chat.