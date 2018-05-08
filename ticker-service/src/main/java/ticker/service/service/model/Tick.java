package ticker.service.service.model;

import java.time.Instant;

public class Tick {

    private final Instant timestamp;
    private final String symbol;
    private final Double price;
    private final Double volume;
    private final Double netChange;

    public Tick(final String symbol, final Double price, final Double volume, final Double netChange) {
        this.timestamp = Instant.now();
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.netChange = netChange;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getNetChange() {
        return netChange;
    }
}
