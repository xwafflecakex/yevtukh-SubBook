package com.example.nynic.yevtukh_subbook;

import android.util.StringBuilderPrinter;

/**
 * Created by nynic on 2018-01-29.
 */

public class Subscription {
    private String name;
    private String date;
    private float charge;
    private String comment = "";

//    public Subscription(String name, String date, float price, String comment) {
//        this.name = name;
//        this.date = date;
//        this.price = price;
//        this.comment = comment;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String printSub(){
        String s = this.name + "//" + this.date + "//" + this.charge + "//" + this.comment;
        return s;
    }
}
