package com.example.android.booklistingapp;

/**
 * Created by Alexa on 8/4/2016.
 */
public class Book {

    private String mTitle;
    private String mAuthor;

    public Book (String title, String author){
        mTitle = title;
        mAuthor = author;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }
}
