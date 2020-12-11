package ticker.service.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import ticker.service.handler.model.TickRequest;
import ticker.service.handler.model.TickResponse;
import ticker.service.service.TickService;

import java.io.IOException;

@Component
public class TickerHandler implements WebSocketHandler {

    private final  TickService tickService;

    private final ObjectMapper mapper = new ObjectMapper();

  public TickerHandler(TickService tickService) {
    this.tickService = tickService;
  }

  @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive()
                .map(webSocketMessage -> {
                    try {
                        ObjectReader reader = mapper.readerFor(TickRequest.class);
                        return (TickRequest) reader.readValue(webSocketMessage.getPayloadAsText());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(tickRequest -> tickService.get(tickRequest.getSymbol()))
                .flatMap(tickFlux -> tickFlux.map(tick -> {
                    try {
                        return session.textMessage(mapper.writeValueAsString(TickResponse.from(tick)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })));
    }
}
