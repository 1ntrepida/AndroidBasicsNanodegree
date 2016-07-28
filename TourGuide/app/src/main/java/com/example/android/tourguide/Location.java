package com.example.android.tourguide;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexa on 7/21/2016.
 */
public class Location implements Parcelable{

    public int imageId;
    public String name;
    public String address;
    public String desc;

    public Location(int imageId, String name, String address, String desc) {
        this.imageId = imageId;
        this.name = name;
        this.address = address;
        this.desc = desc;
    }

    public Location(Parcel in) {
        imageId = in.readInt();
        name = in.readString();
        address = in.readString();
        desc = in.readString();
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

    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(desc);
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>()
    {
        public Location createFromParcel(Parcel in)
        {
            return new Location(in);
        }
        public Location[] newArray(int size)
        {
            return new Location[size];
        }
    };
}
