package ua.ks.itdoc.model;

import org.apache.log4j.Logger;
import ua.ks.itdoc.util.CalculatorUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private int id;
    private int userId;
    private String username;
    private String text;
    private String result;
    private Date pubDate;
    private String pubDateStr;
    private String gravatar;

    //Logger initialization
    private static final Logger log = Logger.getLogger(CalculatorUtil.class);


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public String getResult() throws Exception {
        return result;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
        if (pubDate != null) {
            pubDateStr = new SimpleDateFormat("yyyy-MM-dd @ HH:mm").format(pubDate);
        }
    }

    public String getPubDateStr() {
        return pubDateStr;
    }

    public String getGravatar() {
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public String getCalculate() {
        log.info("Calculate expression " + this.text + " is started.");
        String result;
        try {
            result = CalculatorUtil.calculateString(this.text);
        } catch (Exception e) {
            log.error("ERROR: " + e.getMessage());
            result = e.getMessage();
        }

        log.info("Calculation is OK. Result is: " + result);
        return result;
    }
}