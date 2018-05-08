package ticker.service.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import ticker.service.service.TickService;

@Component
public class AllTickersHandler implements WebSocketHandler {

    @Autowired
    private TickService tickService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(tickService.getAll()
                .map(tick -> {
                    try {
                        return session.textMessage(mapper.writeValueAsString(tick));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }));
    }
}
