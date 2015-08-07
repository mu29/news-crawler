package crawler;

import java.util.Date;

public abstract class Crawler {

    protected String keyWord;
    protected Date startDate;
    protected Date endDate;
    protected String urlString;

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public abstract void makeUrl();

}
