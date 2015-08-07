package data;

public class Post {

    private String title;
    private String link;
    private String contents;

    public Post(String title, String link, String contents) {
        this.title = title;
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

}
