package crawler;

public class GoogleCrawler extends Crawler {

    private String location;

    public GoogleCrawler(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void makeUrl() {

    }

}
