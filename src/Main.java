import crawler.Crawler;
import crawler.GoogleCrawler;
import crawler.NaverCrawler;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    static class Site {
        public static final String NAVER = "0";
        public static final String GOOGLE = "1";
    }

    public static void main(String[] args) {
        String site;
        String keyWord;
        String startDate;
        String endDate;
        int page;

        Scanner sc = new Scanner(System.in);

        System.out.println("검색할 사이트를 입력하세요.");
        System.out.println("    [0] : 네이버");
        System.out.println("    [1] : 구글");
        site = sc.nextLine();
        System.out.println("키워드를 입력하세요.");
        keyWord = sc.nextLine();
        System.out.println("페이지 수를 입력하세요");
        page = Integer.parseInt(sc.nextLine());
        System.out.println("시작 날짜를 입력하세요. (YYYY-MM-DD)");
        startDate = sc.nextLine();
        System.out.println("종료 날짜를 입력하세요. (YYYY-MM-DD)");
        endDate = sc.nextLine();

        Crawler crawler;
        switch (site) {
            case Site.NAVER:
                crawler = new NaverCrawler(keyWord);
                break;
            case Site.GOOGLE:
                crawler = new GoogleCrawler(keyWord);

                System.out.println("검색 지역을 입력하세요.");
                String  location = sc.nextLine();
                ((GoogleCrawler) crawler).setLocation(location);
                break;
            default:
                crawler = new GoogleCrawler(keyWord);
                break;
        }
        crawler.setStartDate(startDate);
        crawler.setEndDate(endDate);
        crawler.setPage(page);

        crawler.run();
    }

}
