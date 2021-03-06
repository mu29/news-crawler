package crawler;

import data.DataAccessor;
import data.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NaverCrawler extends Crawler {

    private final String KEYWORD = "&query=";
    private final String START_DATE = "&startDate=";
    private final String END_DATE = "&endDate=";
    private final String PAGE = "&page=";
    private final String ON_PAPER = "&stPaper=exist:1";
    private final String NEWS_CODE = "&newscode=";

    private boolean onPaper;
    private String code;

    public NaverCrawler(String keyWord) {
        this.keyWord = keyWord;
        this.baseUrl = "http://news.naver.com/main/search/search.nhn?so=rel.dsc&ie=MS949&detail=0&sm=all.basic";
    }

    public void setOnPaper(boolean onPaper) {
        this.onPaper = onPaper;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected void makeUrl() {
        try {
            urlString = baseUrl + KEYWORD + URLEncoder.encode(keyWord, "MS949") + "&pd=";
            if (!startDate.equals("") && !endDate.equals("")) {
                urlString += "4" + START_DATE + startDate + END_DATE + endDate;
            } else {
                urlString += "1";
            }
            if (onPaper)
                urlString += ON_PAPER;
            if (!code.equals("-1"))
                urlString += NEWS_CODE + code;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        makeUrl();
        DataAccessor da = DataAccessor.getInstance();

        for (int i = 1; i <= page; i++) {
            try {
                String url = urlString + PAGE + i;
                Document doc = Jsoup.connect(url).get();
                Elements titleList = doc.select("a.tit");
                Elements dateList = doc.select("span.time");
                Elements contentsList = doc.select("p.dsc");

                for (int n = 0; n < titleList.size(); n++) {
                    String title = titleList.get(n).text();
                    String date = dateList.get(n).text();
                    String link = titleList.get(n).attr("href");
                    String contents = getContents(link, contentsList.get(n).text());
                    Post post = new Post(title, date, link, contents);

                    da.savePost(post);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}