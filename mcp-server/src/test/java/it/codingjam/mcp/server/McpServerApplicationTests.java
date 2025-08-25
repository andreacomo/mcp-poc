package it.codingjam.mcp.server;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootTest
class McpServerApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		withMcp(mcpClient -> {
			McpSchema.CallToolResult callToolResult = mcpClient.callTool(new McpSchema.CallToolRequest(
					"get_city_geolocation",
					Map.of("city", "Prato")));

			callToolResult.content().forEach(content -> {
				System.out.println(content);
			});
		});
	}

	private static void withMcp(Consumer<McpSyncClient> function) {
		Path projectRoot = Paths.get("");
		ServerParameters serverParameters = ServerParameters.builder("java")
				.args("-jar", projectRoot.toAbsolutePath() + "/mcp-server/target/mcp-server-1.0.0-SNAPSHOT.jar")
				.build();
		var stdioTransport = new StdioClientTransport(serverParameters);

		McpSyncClient mcpClient = null;
		try {
			mcpClient = McpClient.sync(stdioTransport).build();
			mcpClient.initialize();

			function.accept(mcpClient);
		} finally {
			if (mcpClient != null) {
				mcpClient.closeGracefully();
			}
		}
	}

}
