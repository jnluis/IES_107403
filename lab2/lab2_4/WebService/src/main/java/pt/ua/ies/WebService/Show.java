package pt.ua.ies.WebService;

public class Show {

    private String Showid;
    private final String content;

    public Show(String id, String content) {
        this.Showid = id;
        this.content = content;
    }

    public String getId() {
        return Showid;
    }

    public String getContent() {
        return content;
    }
}
