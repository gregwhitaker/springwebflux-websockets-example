package ticker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import ticker.service.handler.TickerHandler;
import ticker.service.handler.AllTickersHandler;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TickerServiceApplication {

    public static void main(String... args) throws Exception {
        SpringApplication.run(TickerServiceApplication.class, args);
    }

    @Bean
    @Autowired
    public HandlerMapping handlerMapping(AllTickersHandler tickersHandler, TickerHandler tickerHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/tickers", tickersHandler);
        map.put("/tickers/{id}", tickerHandler);

        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(map);
        return handlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter webSocketHandler() {
        return new WebSocketHandlerAdapter();
    }
}
