package pt.ua.ies.ex3.entity;

public class NewQuote {

    private String quote;
    private int showId;

    public NewQuote(String quote, int showId) {
        this.quote = quote;
        this.showId = showId;
    }

    public String getQuote() {
        return quote;
    }


    public int getShowId() {
        return showId;
    }
}
