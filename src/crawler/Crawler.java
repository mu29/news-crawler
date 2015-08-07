package crawler;

import java.util.Date;

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

    protected abstract void makeUrl();

    public abstract void run();

}
