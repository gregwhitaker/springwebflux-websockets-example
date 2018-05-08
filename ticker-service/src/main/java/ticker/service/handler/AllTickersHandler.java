package ticker.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import ticker.service.service.TickService;

@Component
public class AllTickersHandler implements WebSocketHandler {

    @Autowired
    private TickService quoteService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return null;
    }
}
