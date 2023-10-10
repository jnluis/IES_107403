package pt.ua.ies.WebService;

public class Quote {

    private String id;
    private String quote;

    public Quote(String id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public String getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }
}