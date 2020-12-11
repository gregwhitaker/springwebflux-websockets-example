package ticker.service.handler.model;

import java.io.Serializable;

public class TickRequest implements Serializable {

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
