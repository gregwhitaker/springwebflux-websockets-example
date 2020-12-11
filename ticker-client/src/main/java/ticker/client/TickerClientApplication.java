package ticker.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;
import ticker.client.model.TickRequest;
import ticker.client.model.TickResponse;

import java.net.URI;

@SpringBootApplication
public class TickerClientApplication {
    private static final Logger LOG = LoggerFactory.getLogger(TickerClientApplication.class);

    public static void main(String... args) {
        SpringApplication app = new SpringApplication(TickerClientApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    @Component
    public class Runner implements CommandLineRunner {

        public static final String WS_TICKERS = "ws://localhost:8080/tickers";

        @Override
        public void run(String... args) throws Exception {
            if (args.length == 0) {
                LOG.info("Requesting all tickers...");

                WebSocketClient client = new ReactorNettyWebSocketClient();
                client.execute(
                        URI.create(WS_TICKERS),
                        session -> session.send(
                                Mono.empty())
                                .thenMany(session.receive()
                                        .map(webSocketMessage -> {
                                            try {
                                                TickResponse tickResponse = mapper.readerFor(TickResponse.class).readValue(webSocketMessage.getPayloadAsText());
                                                LOG.info("[symbol: '{}', price: '{}', volume: '{}']", tickResponse.getSymbol(), tickResponse.getPrice(), tickResponse.getVolume());
                                                return tickResponse;
                                            } catch (JsonProcessingException e) {
                                                LOG.error("Error occurred deserializing response", e);
                                                return null;
                                            }
                                        }))
                                .then())
                        .block();
            } else if (args.length == 1) {
                final String symbol = args[0];

                LOG.info("Requesting ticker '{}'...", symbol);

                String tickRequest = mapper.writeValueAsString(new TickRequest(symbol));

                WebSocketClient client = new ReactorNettyWebSocketClient();
                client.execute(
                        URI.create("ws://localhost:8080/tickers/" + symbol.toUpperCase()),
                        session -> session.send(Mono.just(session.textMessage(tickRequest)))
                                .thenMany(session.receive()
                                        .map(webSocketMessage -> {
                                            try {
                                                TickResponse tickResponse = mapper.readerFor(TickResponse.class).readValue(webSocketMessage.getPayloadAsText());
                                                LOG.info("[symbol: '{}', price: '{}', volume: '{}']", tickResponse.getSymbol(), tickResponse.getPrice(), tickResponse.getVolume());
                                                return tickResponse;
                                            } catch (JsonProcessingException e) {
                                                LOG.error("Error occurred deserializing response", e);
                                                return null;
                                            }
                                        }))
                                .then())
                        .block();
            } else {
                throw new IllegalArgumentException("Incorrect arguments!");
            }
        }
    }
}
