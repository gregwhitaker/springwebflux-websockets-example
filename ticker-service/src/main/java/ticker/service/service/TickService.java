package ticker.service.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ticker.service.service.model.Tick;

@Service
public class TickService {

    public Flux<Tick> getAll() {
        return null;
    }

    public Flux<Tick> get(String symbol) {
        return null;
    }
}
