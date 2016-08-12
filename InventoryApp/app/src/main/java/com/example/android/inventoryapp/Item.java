package com.example.android.inventoryapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexa on 8/10/2016.
 */
public class Item implements Parcelable{

    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Item (Parcel in){
        
    }
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
