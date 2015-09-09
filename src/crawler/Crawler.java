package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Crawler {

    protected String keyWord;
    protected String startDate;
    protected String endDate;
    protected String baseUrl;
    protected String urlString;
    protected int page;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPage(int page) {
        this.page = page;
    }

    protected String getContents(String url, String sample) {
        List<String> contentsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div");
            for (Element e : elements) {
                if (isMatch(sample, e.text()))
                    contentsList.add(e.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(contentsList, new StringLengthListSort());

        String longest = contentsList.size() > 0 ? contentsList.get(contentsList.size() - 1) : "기사 내용을 가져올 수 없습니다.";
        longest = longest.replace(" ", "");
        String contents = contentsList.size() > 0 ? contentsList.get(0) : "기사 내용을 가져올 수 없습니다.";

        Pattern pattern = Pattern.compile("[0-9]{2,4}[^0-9a-zA-Z]{1}[0-9]{2}[^0-9a-zA-Z]{1}[0-9]{2}[^0-9a-zA-Z]?");
        Matcher match = pattern.matcher(longest);
        if (match.find())
            return "[|" + match.group(0) + "|]" + contents;

        return "[날짜를 찾을 수 없습니다]" + contents;
    }

    protected boolean isMatch(String sample, String text) {
        if (sample.length() * 2 > text.length())
            return false;

        int matchCount = 0;
        String[] samples = sample.split(" ");
        for (String s : samples) {
            if (text.contains(s))
                matchCount++;
        }

        return (matchCount * 100 / samples.length) > 30;
    }

    class StringLengthListSort implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    protected abstract void makeUrl();

    public abstract void run();

}
