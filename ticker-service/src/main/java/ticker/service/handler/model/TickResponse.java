package ticker.service.handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ticker.service.service.model.Tick;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@JsonPropertyOrder({
        "timestamp",
        "symbol",
        "name",
        "price",
        "volume"
})
public class TickResponse {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd hh:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static TickResponse from(Tick tick) {
        TickResponse response = new TickResponse();
        response.setTimestamp(DATE_TIME_FORMATTER.format(tick.getTimestamp()));
        response.setSymbol(tick.getSymbol());
        response.setName(tick.getName());
        response.setPrice(tick.getPrice());
        response.setVolume(tick.getVolume());

        return response;
    }

    private String timestamp;
    private String symbol;
    private String name;
    private Double price;
    private Long volume;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
