package com.example.android.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexa on 8/4/2016.
 */
public class Book implements Parcelable{

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

    //parcelable stuff starts here

    public Book(Parcel in){
        mTitle = in.readString();
        mAuthor = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTitle);
        out.writeString(mAuthor);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
