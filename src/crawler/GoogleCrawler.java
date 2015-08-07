package crawler;

import data.DataAccessor;
import data.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URLEncoder;

public class GoogleCrawler extends Crawler {

    private final String KEYWORD = "&q=";
    private final String START_DATE = "&tbs=cdr%3A1%2Ccd_min%3A";
    private final String END_DATE = "%2Ccd_max%3A";
    private final String PAGE = "&start=";

    private String location;

    public GoogleCrawler(String keyWord) {
        this.keyWord = keyWord;
        this.baseUrl = "https://www.google.co.kr/search?tbm=nws";
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setEndDate(String endDate) {
        if (!endDate.equals(""))
            endDate = endDate.replace("-", ".+") + ".";
        super.setEndDate(endDate);
    }

    @Override
    public void setStartDate(String startDate) {
        if (!startDate.equals(""))
           startDate = startDate.replace("-", ".+") + ".";
        super.setStartDate(startDate);
    }

    @Override
    protected void makeUrl() {
        try {
            urlString = baseUrl + KEYWORD + URLEncoder.encode(keyWord, "MS949");
            if (!startDate.equals("") && !endDate.equals("")) {
                urlString += START_DATE + startDate + END_DATE + endDate;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        makeUrl();
        DataAccessor da = DataAccessor.getInstance();

        for (int i = 0; i < page; i++) {
            try {
                String url = urlString + PAGE + (i * 10);
                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                Elements titleList = doc.select("h3");
                Elements contentsList = doc.select("div.st");

                for (int n = 0; n < titleList.size(); n++) {
                    String title = titleList.get(n).text();
                    String link = titleList.get(n).childNode(0).attr("href");
                    String contents = getContents(link, contentsList.get(n).text());
                    Post post = new Post(title, link, contents);

                    da.savePost(post);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
