package ticker.service.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import ticker.service.handler.model.TickResponse;
import ticker.service.service.TickService;

@Component
public class AllTickersHandler implements WebSocketHandler {

    private final TickService tickService;

    private final ObjectMapper mapper = new ObjectMapper();

  public AllTickersHandler(TickService tickService) {
    this.tickService = tickService;
  }

  @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(tickService.getAll(true)
                .map(tick -> {
                    try {
                        return session.textMessage(mapper.writeValueAsString(TickResponse.from(tick)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }));
    }
}
