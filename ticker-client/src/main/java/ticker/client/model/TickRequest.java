package ticker.client.model;

public class TickRequest {

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
