package crawler;

import java.net.URLEncoder;

public class GoogleCrawler extends Crawler {

    private final String KEYWORD = "&q=";
    private final String START_DATE = "&startDate=";
    private final String END_DATE = "&endDate=";

    private String location;

    public GoogleCrawler(String keyWord) {
        this.keyWord = keyWord;
        this.baseUrl = "https://www.google.co.kr/?#newwindow=1";
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    protected void makeUrl() {
        try {
            urlString = baseUrl + KEYWORD + URLEncoder.encode(keyWord, "UTF-8") + "&pd=";
            if (!startDate.equals("") && !endDate.equals("")) {
                urlString += START_DATE + startDate + END_DATE + endDate;
            } else {
                urlString += "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

}
