package ticker.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ticker.service.service.model.Tick;

import java.time.Duration;

@Service
public class TickService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TickService.class);

    public Flux<Tick> getAll() {
        return Flux.interval(Duration.ofMillis(1000L))
                .map(aLong -> {
                   return new Tick("NTF", 110.23, 134.6, 22.35);
                });
    }

    public Flux<Tick> get(String symbol) {
        return null;
    }
}
