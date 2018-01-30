package com.example.nynic.yevtukh_subbook;

/**
 * Created by nynic on 2018-01-29.
 */

public class Subscription {
    private String name;
    private String date;
    private double price;
    private String comment;

    public Subscription(String name, String date, double price, String comment) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.comment = comment;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
