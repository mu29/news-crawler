package data;

public class Post {

    private String title;
    private String date;
    private String link;
    private String contents;

    public Post(String title, String date, String link, String contents) {
        this.title = title.replace("\"", "");
        this.date = date;
        this.link = link;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getContents() {
        return contents;
    }

    public String getDate() {
        return date;
    }
}
