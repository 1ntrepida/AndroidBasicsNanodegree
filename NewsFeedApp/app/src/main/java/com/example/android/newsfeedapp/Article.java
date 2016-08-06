package com.example.android.newsfeedapp;

/**
 * Created by Alexa on 8/5/2016.
 */
public class Article {

    private String headline;
    private String url;

    public Article(String headline, String url){
        this.headline = headline;
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public String getUrl() {
        return url;
    }
}
