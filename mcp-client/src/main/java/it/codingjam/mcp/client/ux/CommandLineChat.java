package it.codingjam.mcp.client.ux;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import it.codingjam.mcp.client.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

@Component
public class CommandLineChat implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineChat.class);

    private static final String PREFIX_REQ = ">>> ";
    private static final String PREFIX_RESP = "<<< ";

    private final ChatService chatService;

    private final List<McpSyncClient> clients;

    public CommandLineChat(ChatService chatService, List<McpSyncClient> clients) {
        this.chatService = chatService;
        this.clients = clients;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Available tools: {}", clients.stream()
                .flatMap(c -> c.listTools().tools().stream())
                .map(McpSchema.Tool::name)
                .toList());

        onNewRequest(chatService::chat);
    }

    private void onNewRequest(Function<String, Flux<String>> answerRequest) {
        System.out.println("Scrivi qualcosa (CTRL+C per terminare):");
        System.out.print(PREFIX_REQ);
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();

                answerRequest.apply(request)
                        .doFirst(() -> System.out.print(PREFIX_RESP))
                        .doOnNext(System.out::print)
                        .doFinally(s -> {
                            System.out.println();
                            System.out.println();
                            System.out.print(PREFIX_REQ);
                        })
                        .subscribe();
            }
        }
    }
}
