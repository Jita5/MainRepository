package core;

import java.util.*;

public class HistoryObject {

    private String url; // Address
    private Date revisit; // Date when re-crawling is allowed

    public HistoryObject(String url, Date rv) {
        this.url = url;
        this.revisit = rv;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRevisit() {
        return revisit;
    }

    public void setRevisit(Date revisit) {
        this.revisit = revisit;
    }

    @Override
    public String toString() {
        return url;
    }
}
