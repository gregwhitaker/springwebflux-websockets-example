package ticker.client.model;

import java.io.Serializable;

public class TickRequest implements Serializable {

    private String symbol;

    public TickRequest(final String symbol) {
        this.symbol = symbol.toUpperCase();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
