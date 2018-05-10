package ticker.service.service.model;

import java.time.Instant;

public class Tick {

    private final Instant timestamp;
    private final String symbol;
    private final String name;
    private final Double price;
    private final Long volume;
    private final Float netChange;

    public Tick(final String symbol, final String name, final Double price,
                final Long volume, final Float netChange) {
        this.timestamp = Instant.now();
        this.symbol = symbol;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Long getVolume() {
        return volume;
    }

    public Float getNetChange() {
        return netChange;
    }
}
