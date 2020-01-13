package ticker.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@SpringBootApplication
public class TickerClientApplication {

    public static void main(String... args) throws Exception {
        SpringApplication app = new SpringApplication(TickerClientApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Component
    public class Runner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {

            WebSocketClient client = new ReactorNettyWebSocketClient();
            client.execute(
                    URI.create("ws://localhost:8080/tickers"),
                    session -> session.send(
                            Mono.empty())
                            .thenMany(session.receive()
                                    .map(WebSocketMessage::getPayloadAsText)
                                    .log())
                            .then())
                    .block(Duration.ofSeconds(60L));
        }
    }
}
