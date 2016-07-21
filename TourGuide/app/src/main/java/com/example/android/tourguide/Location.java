package com.example.android.tourguide;

/**
 * Created by Alexa on 7/21/2016.
 */
public class Location {

    private int imageId;
    private String name;
    private String address;
    private String desc;

    public Location(int imageId, String name, String address, String desc) {
        this.imageId = imageId;
        this.name = name;
        this.address = address;
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDesc() {
        return desc;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

}
